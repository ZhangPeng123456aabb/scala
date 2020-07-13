package com.baizhi.eventtime
import java.text.SimpleDateFormat

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.function.AllWindowFunction
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

object FlinkWordCountsTumblingTimeWindow02 {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    fsEnv.setParallelism(1)
    //设置时间特性
    fsEnv.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    //设置水位线计算频率
    fsEnv.getConfig.setAutoWatermarkInterval(1000)
    val ds = fsEnv.socketTextStream("Spark",5555)
    ds.map(_.split("\\s+"))
      .map(ts=>(ts(0),ts(1).toLong))
      .assignTimestampsAndWatermarks(new UserDefineAssignerWithPeriodcWatermarks)
      .windowAll(TumblingEventTimeWindows.of(Time.seconds(5)))
      .allowedLateness(Time.seconds(2))
      .apply(new AllWindowFunction[(String,Long),String,TimeWindow]{
        val dsf = new SimpleDateFormat("HH:ss:mm")
        override def apply(window: TimeWindow,
                           input: Iterable[(String, Long)],
                           out: Collector[String]): Unit = {
          println(dsf.format(window.getStart)+"~"+dsf.format(window.getEnd))
          out.collect(input.map(t=>t._1+"->"+dsf.format(t._2)).reduce((v1,v2)=>v1+"|"+v2))
        }
      })
      .print()
      fsEnv.execute("FlinkWordCountsTumblingTimeWindow02")
  }
}
