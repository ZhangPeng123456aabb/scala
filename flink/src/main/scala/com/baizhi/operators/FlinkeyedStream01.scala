package com.baizhi.operators
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FlinkeyedStream01 {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val logStream = fsEnv.socketTextStream("Spark",5555)
    logStream.flatMap(_.split("\\s+"))
      .map(WordPair5(_,1))
      .keyBy("word")
      .sum("count")
      .print()
    fsEnv.execute("FlinkeyedStream01")
  }
}
case class WordPair5(word:String,count:Int)