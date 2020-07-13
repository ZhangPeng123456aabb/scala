package com.baizhi.chains
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FlinkWorldCountOperatorChain02 {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val ds = fsEnv.socketTextStream("Spark",5555)
    ds.filter(line=>(line.startsWith("INFO")))
      .flatMap(_.split("\\s+"))
      .map((_,1))
      .map(t=>WordPairs1(t._1,t._2))
      .keyBy("word")
      .sum("count")
      .print("WordPairs")
    fsEnv.execute("FlinkWorldCountOperatorChain02INF")
  }
}
case class WordPairs1(word:String,count:Int)