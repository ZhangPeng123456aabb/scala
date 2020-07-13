package streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object QueueRdd {
  def main(args: Array[String]): Unit = {
    //设置StreamingContext程序的入口
    val conf = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //每隔五秒划分一个个micro batche(RDD数据)
    val ssc = new StreamingContext(conf,Seconds(5))
    //设置日志级别
    ssc.sparkContext.setLogLevel("ERROR")
    val sc = ssc.sparkContext
    val rdd1 = sc.textFile("file:///I:\\data\\test.txt",2)
    val rdd2 = sc.textFile("file:///I:\\data\\test1.txt",2)
    val rdd3 = sc.textFile("file:///I:\\data\\test2.txt",2)
    val queue = mutable.Queue(rdd1,rdd2,rdd3)
    val stream = ssc.queueStream(queue)
    stream.print()
    //启动流数据的处理
    ssc.start()
    //优雅关闭StreamingContext应用
    ssc.awaitTermination()
  }
}
