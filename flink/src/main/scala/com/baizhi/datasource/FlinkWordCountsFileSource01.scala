package com.baizhi.datasource

import org.apache.flink.streaming.api.scala._
object FlinkWordCountsFileSource01 {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val filePath="file:///I:\\data"
    val dataStream = fsEnv.readTextFile(filePath)
    dataStream.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .print()
    fsEnv.execute("FlinkWordCountsFileSource01")
  }
}
