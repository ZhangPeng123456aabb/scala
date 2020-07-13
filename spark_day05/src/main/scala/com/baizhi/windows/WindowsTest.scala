package com.baizhi.windows

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WindowsTest {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local[*]").setAppName("windows")
    val ssc=new StreamingContext(conf,Seconds(1))
    ssc.sparkContext.setLogLevel("ERROR")
    val dstream = ssc.socketTextStream("spark",8888)
    dstream
      .flatMap(_.split(" "))
      .map((_,1))
       //设定长度为10，滑动步长约5
    //单个参数的window，严格意义上来说并不是翻滚窗口，本质跳跃窗口（长度为指定的length，滑动步长为默认值1s）
      .window(Seconds(10),Seconds(5))
      .reduceByKey(_+_)
      .print()
       ssc.start()
       ssc.awaitTermination()
  }
}
