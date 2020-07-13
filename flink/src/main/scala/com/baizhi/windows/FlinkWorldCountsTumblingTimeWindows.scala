package com.baizhi.windows
import org.apache.flink.api.common.functions.ReduceFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time

object FlinkWorldCountsTumblingTimeWindows {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //DataStream 细化
    val ds = fsEnv.socketTextStream("Spark",6666)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
      .reduce(new ReduceFunction[(String, Int)] {
        override def reduce(t: (String, Int), t1: (String, Int)): (String, Int) = {
          (t._1,(t._2+t1._2))
        }
      })
      .print()
    fsEnv.execute("FlinkWorldCountsTumblingTimeWindows")
  }
}
