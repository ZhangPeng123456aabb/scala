package streaming

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 实时计算（单词计数）
  */
object InputDataSourceTest {
  def main(args: Array[String]): Unit = {
    //1.创建streamingContext程序入口
    val conf = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //2.将DStream按照5秒时间间隔划分一个个的micro batch数据(RDD序列)
    val ssc = new StreamingContext(conf,Seconds(5))
    //3.关闭streaming默认的INFO级别的日志
    ssc.sparkContext.setLogLevel("ERROR")
    //4.创建DStream数据流
    val stream = ssc.socketTextStream("Spark", 4444)
    //5.对DStream应用高级的算子
    stream.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()
    //6.启动流数据处理应用
    ssc.start()
    //7.优雅的关闭Streaming应用
    ssc.awaitTermination()
  }
}
