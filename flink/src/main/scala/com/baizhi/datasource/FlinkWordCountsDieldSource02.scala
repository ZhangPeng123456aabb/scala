package com.baizhi.datasource

import org.apache.flink.api.java.io.TextInputFormat
import org.apache.flink.streaming.api.scala._
object FlinkWordCountsDieldSource02 {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream-细化
    val filePath = "file:///I:\\data"
    val inputFormat = new TextInputFormat(null)
    val dataStream = fsEnv.readFile(inputFormat,filePath)
    //对数据做转换
    dataStream.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .print()
    fsEnv.execute("FlinkWordCountsDieldSource02")
  }
}
