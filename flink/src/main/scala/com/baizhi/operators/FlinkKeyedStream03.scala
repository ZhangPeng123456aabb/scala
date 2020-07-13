package com.baizhi.operators
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FlinkKeyedStream03 {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.createLocalEnvironment(3)
    val ds = fsEnv.socketTextStream("Spark",5555)
    ds.flatMap(_.split("\\s+"))
      .map(WordPairs3(_,1))
      .keyBy("word")
      .fold(("",0))((v3,v4)=>(v4.word,v3._2+v4.count))
      .print()
    fsEnv.execute("FlinkKeyedStream03")
  }
}
case class WordPairs3(word:String,count:Int)