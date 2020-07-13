package com.baizhi.quickstart

import org.apache.flink.streaming.api.scala._
object FlinkWordCountsQuickStart03 {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val jarFiles = "flink\\target\\flink-1.0-SNAPSHOT-jar-with-dependencies.jar"
    val fsEnv = StreamExecutionEnvironment.createRemoteEnvironment("Spark",8081,jarFiles)
    fsEnv.setParallelism(2)
    //创建DataStream -细化
    val ds = fsEnv.socketTextStream("Spark",5555)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .print()
    fsEnv.execute("FlinkWordCountsQuickStart03")
  }
}
