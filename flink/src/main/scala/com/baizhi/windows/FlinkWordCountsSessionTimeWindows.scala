package com.baizhi.windows
import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.assigners.ProcessingTimeSessionWindows
import org.apache.flink.streaming.api.windowing.time.Time

object FlinkWordCountsSessionTimeWindows {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val ds = fsEnv.socketTextStream("Spark",6666)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .window(ProcessingTimeSessionWindows.withGap(Time.seconds(5)))
      .aggregate(new AggregateFunction[(String,Int),(String,Int),(String,Int)] {
        override def createAccumulator(): (String, Int) = ("",0)

        override def add(value: (String, Int), accumulator: (String, Int)): (String, Int) = {
          (value._1,(value._2+accumulator._2))
        }

        override def getResult(acc: (String, Int)): (String, Int) = acc

        override def merge(acc: (String, Int), acc1: (String, Int)): (String, Int) = {
          (acc._1,(acc._2+acc1._2))
        }
      })
      .print()
    fsEnv.execute("FlinkWordCountsSessionTimeWindows")
  }
}
