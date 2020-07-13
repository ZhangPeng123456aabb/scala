package com.baizhi.chains
import org.apache.flink.streaming.api.scala._
object FlinkWordCountOperatorChain04 {
  def main(args: Array[String]): Unit = {
    //1.创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment

    //2.创建DataStream -细化
    val dataStream: DataStream[String] = fsEnv.socketTextStream("Spark",5555)

    //3.对数据做转换
    dataStream.filter(line => line.startsWith("INFO"))
      .flatMap(_.split("\\s+"))
      .disableChaining()
      .map((_,1))
      .map(t=>WordPairs6(t._1,t._2))
      .print()

    fsEnv.execute("FlinkWordCountsQuickStart")
  }
}
case class WordPairs6(word:String,count:Int)