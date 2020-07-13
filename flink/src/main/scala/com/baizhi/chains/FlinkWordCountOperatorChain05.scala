package com.baizhi.chains
import org.apache.flink.streaming.api.scala._
object FlinkWordCountOperatorChain05 {
  def main(args: Array[String]): Unit = {
    //1.创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //2.创建DataStream -细化
    val dataStream: DataStream[String] = fsEnv.socketTextStream("Spark",9999)

    //3.对数据做转换
    dataStream.filter(line => line.startsWith("INFO"))
      .flatMap(_.split("\\s+"))
      .startNewChain()
      .slotSharingGroup("g1")
      .map((_,1))
      .map(t=>WordPairs7(t._1,t._2))
      .print()

    fsEnv.execute("FlinkWordCountsQuickStart")

  }
}
case class WordPairs7(word:String,count:Int)