package com.baizhi.datasource

import org.apache.flink.streaming.api.scala._
object FlinkWordCountsCollection {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val dataStream = fsEnv.fromCollection(List("this is a demo","hello world"))
    dataStream
      .flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .print()
    fsEnv.execute("FlinkWordCountsCollection")
  }
}
