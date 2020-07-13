package com.baizhi.eventtime
import java.text.SimpleDateFormat

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.AllWindowFunction
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

object FlinkWordCountsTumblingTimeWindow03 {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    fsEnv.setParallelism(1)
    fsEnv.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    fsEnv.getConfig.setAutoWatermarkInterval(1000)
    val ds = fsEnv.socketTextStream("Spark",5555)
    val lateTag = new OutputTag[(String,Long)]("late")
    val stream = ds.map(_.split("\\s+"))
      .map(ts=>(ts(0),ts(1).toLong))
      .assignTimestampsAndWatermarks(new UserDefineAssignerWithPeriodcWatermarks)
      .windowAll(TumblingEventTimeWindows.of(Time.seconds(5)))
      .allowedLateness(Time.seconds(2))
      .sideOutputLateData(lateTag)
      .apply(new AllWindowFunction[(String,Long),String,TimeWindow] {
        val dsf = new SimpleDateFormat("HH:mm:ss")
        override def apply(window: TimeWindow,
                           input: Iterable[(String, Long)],
                           out: Collector[String]): Unit ={
          println(dsf.format(window.getStart)+"~"+dsf.format(window.getEnd))
          out.collect(input.map(t=>t._1+"->"+dsf.format(t._2)).reduce((v1,v2)=>v1+"|"+v2))
        }
      })
      stream.print("窗口")
      stream.getSideOutput(lateTag).print("迟到数据")
      fsEnv.execute("FlinkWordCountsTumblingTimeWindow03")
  }
}
