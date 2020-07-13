package com.baizhi.chains
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FlinkWorldCountOperatorChain01 {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val ds = fsEnv.socketTextStream("Spark",5555)
    ds.filter(line=>(line.startsWith("INFO")))
      .flatMap(_.split("\\s+"))
      .map(WordPairs2(_,1))
      .keyBy("word")
      .sum("count")
      .print()
    fsEnv.execute("FlinkWorldCountOperatorChain01")
  }
}
case class WordPairs2(word:String,count:Int)