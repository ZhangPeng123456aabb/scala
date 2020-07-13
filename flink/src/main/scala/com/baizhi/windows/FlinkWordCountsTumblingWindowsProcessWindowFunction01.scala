package com.baizhi.windows
import java.text.SimpleDateFormat

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

object FlinkWordCountsTumblingWindowsProcessWindowFunction01 {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream
    val ds = fsEnv.socketTextStream("Spark",6666)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(_._1)
      .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
      .process(new ProcessWindowFunction[(String,Int),(String,Int),String,TimeWindow] {
        override def process(key: String,
                             context: Context,
                             elements: Iterable[(String, Int)],
                             out: Collector[(String, Int)]): Unit = {
          val w = context.window
          val sdf = new SimpleDateFormat("HH:mm:ss")
          println(sdf.format(w.getStart)+"~"+sdf.format(w.getEnd))
          val total = elements.map(_._2).sum
          out.collect((key,total))
        }
      })
      .print()
    fsEnv.execute("FlinkWordCountsTumblingWindowsProcessWindowFunction01")
  }
}
