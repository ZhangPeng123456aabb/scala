package com.baizhi.quickstart

import org.apache.flink.streaming.api.scala._
object FlinkWordCountsQuickStart01 {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.createLocalEnvironment(3)
    //创建DataStream -细化
    val ds = fsEnv.socketTextStream("Spark",9999)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .print()

    fsEnv.execute("FlinkWordCountsQuickStart01")
  }
}
