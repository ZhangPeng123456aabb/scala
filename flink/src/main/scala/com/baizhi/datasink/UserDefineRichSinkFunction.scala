package com.baizhi.datasink

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
object UserDefineRichSinkFunction {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val ds = fsEnv.socketTextStream("Spark",5555)
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .addSink(new UserfindRichSinkFunction)

    fsEnv.execute("UserDefineRichSinkFunction")
  }
}
