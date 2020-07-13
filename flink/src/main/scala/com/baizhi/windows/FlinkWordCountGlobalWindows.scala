package com.baizhi.windows
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows
import org.apache.flink.streaming.api.windowing.triggers.CountTrigger
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow
import org.apache.flink.util.Collector

object FlinkWordCountGlobalWindows {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val ds = fsEnv.socketTextStream("Spark",6666)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(_._1)
      .window(GlobalWindows.create())
      .trigger(CountTrigger.of(4))
      .apply(new WindowFunction[(String,Int),(String,Int),String,GlobalWindow] {
        override def apply(key: String,
                           window: GlobalWindow,
                           input: Iterable[(String, Int)],
                           out: Collector[(String, Int)]): Unit = {
          println("key:"+key+"w:"+window)
          input.foreach(t=>println(t))
          out.collect(key,input.map(_._2).sum)
        }
      })
      .print()
    fsEnv.execute("FlinkWordCountGlobalWindows")
  }
}
