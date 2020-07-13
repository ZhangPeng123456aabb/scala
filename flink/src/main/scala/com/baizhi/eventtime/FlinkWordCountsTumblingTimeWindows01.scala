package com.baizhi.eventtime
import java.text.SimpleDateFormat

import org.apache.flink.api.common.functions.ReduceFunction
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.{AllWindowFunction, WindowFunction}
import org.apache.flink.streaming.api.windowing.assigners.{TumblingEventTimeWindows, TumblingProcessingTimeWindows}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector


object FlinkWordCountsTumblingTimeWindows01 {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    fsEnv.setParallelism(1)
    //设置时间特性
    fsEnv.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    //设置水位线计算频率 1s
    fsEnv.getConfig.setAutoWatermarkInterval(1000)

    //2.创建DataStream -细化
    val dataStream: DataStream[String] = fsEnv.socketTextStream("Spark",5555)

    //3.对数据做转换
    //a 时间戳
    dataStream.map(_.split("\\s+"))
      .map(ts=>(ts(0),ts(1).toLong))
      .assignTimestampsAndWatermarks(new UserDefineAssignerWithPeriodcWatermarks)
      .windowAll(TumblingEventTimeWindows.of(Time.seconds(5)))
      .apply(new AllWindowFunction[(String,Long),String,TimeWindow] {
        var sdf=new SimpleDateFormat("HH:mm:ss")

        override def apply(window: TimeWindow,
                           input: Iterable[(String, Long)],
                           out: Collector[String]): Unit = {
          println(sdf.format(window.getStart)+" ~ "+ sdf.format(window.getEnd))
          out.collect(input.map(t=>t._1+"->" +sdf.format(t._2)).reduce((v1,v2)=>v1+" | "+v2))
        }
      })
      .print()

    fsEnv.execute("FlinkWordCountsQuickStart")
  }
}
