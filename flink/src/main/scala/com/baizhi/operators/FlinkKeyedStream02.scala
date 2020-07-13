package com.baizhi.operators
import org.apache.flink.streaming.api.scala._
object FlinkKeyedStream02 {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val logStream = fsEnv.socketTextStream("Spark",5555)
    logStream
      .flatMap(_.split("\\s+"))
      .map(WordPairs4(_,1))
      .keyBy("word")
      .reduce((v1,v2)=>WordPairs4(v1.word,v1.count+v2.count))
      .print()
    fsEnv.execute("FlinkKeyedStream02")
  }
}
case class WordPairs4(word:String,count:Int)