package com.baizhi.windows

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WindowTransformationsTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("wondows Test")
    val ssc = new StreamingContext(conf,Seconds(1))
    ssc.sparkContext.setLogLevel("ERROR")
    ssc.checkpoint("hdfs://Spark:9000/checkpoint4")
    val dstream = ssc.socketTextStream("spark",8888)
//    dstream
//      .flatMap(_.split(" "))
//      .map((_,1))
//      // 设定一个5s翻滚窗口 统计窗口内元素的个数
//      //.countByWindow(Seconds(5),Seconds(5))
//      .print()
    //==============================================
//    dstream
//        .flatMap(_.split(" "))
//        .map(_.toInt)
//        .reduceByWindow((v1,v2)=>v1+v2,Seconds(5),Seconds(5))
//        .print()
//    dstream
//        .flatMap(_.split(" "))
//        .map((_,1))
//        .reduceByKeyAndWindow((v1:Int,v2:Int)=>v1+v2,Seconds(5),Seconds(5))
//        .print()
//    dstream
//        .flatMap(_.split(" "))
//        .map((_,1))
//        .reduceByKeyAndWindow((v1:Int,v2:Int)=>v1+v2,(v1:Int,v2:Int)=>v1-v2,Seconds(5),Seconds(5))
//        .filter(_._2!=0)
//        .print()
    dstream
        .flatMap(_.split(" "))
      // 对一个值调用返回一个（k,long） 相同k value的出现次数
        .countByValueAndWindow(Seconds(5),Seconds(3))
        .print()
    ssc.start()
    ssc.awaitTermination()
  }
}
