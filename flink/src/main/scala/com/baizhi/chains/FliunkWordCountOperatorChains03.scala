package com.baizhi.chains
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FliunkWordCountOperatorChains03 {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val ds = fsEnv.socketTextStream("Spark",5555)
    ds.filter(line=>(line.startsWith("INFO")))
      .flatMap(_.split("\\s+"))
      .map((_,1))
      .startNewChain()
      .map(t=>WordCounts(t._1,t._2))
      .keyBy("word")
      .sum("count")
      .print("word")
      fsEnv.execute("FliunkWordCountOperatorChains03")
  }
}
case class WordCounts(word:String,Count:Int)