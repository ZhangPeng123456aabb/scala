package com.baizhi.datasource
import org.apache.flink.streaming.api.scala._
object FlinkCountsCollection01 {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val dataStream = fsEnv.fromCollection(List("My name is ZhangPeng"))
    dataStream
      .flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      //.print()
        //.printToErr("right")
        .print("ERROR")
    fsEnv.execute("FlinkCountsCollection01")
  }
}
