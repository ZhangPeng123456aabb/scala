package com.baizhi.datasink
import org.apache.flink.streaming.api.scala._
object MapFlunctions {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val s1 = fsEnv.socketTextStream("Spark",5555)
    val s2 = fsEnv.socketTextStream("Spark",6666)
    s1.connect(s2).flatMap(
      (line:String)=>line.split("\\s+"),
      (line:String)=>line.split("\\s+")
    )
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .print()
    fsEnv.execute("MapFlunctions")
  }
}
