package com.baizhi.windows
import org.apache.flink.api.common.functions.FoldFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time

object FlinkWordCountsSlidingTimeWindows {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val ds = fsEnv.socketTextStream("Spark",6666)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .window(SlidingProcessingTimeWindows.of(Time.seconds(4),Time.seconds(2)))
      .fold(("",0),new FoldFunction[(String,Int),(String,Int)] {
        override def fold(accumulator: (String, Int), value: (String, Int)): (String, Int) = {
          (value._1,accumulator._2+value._2)
        }
      })
      .print()
    fsEnv.execute("FlinkWordCountsSlidingTimeWindows")
  }
}
