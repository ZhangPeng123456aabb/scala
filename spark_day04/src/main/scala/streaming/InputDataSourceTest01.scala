package streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object InputDataSourceTest01 {
  def main(args: Array[String]): Unit = {
    //1.创建StreamingContext程序的入口
    val conf = new SparkConf().setMaster("local[*]").setAppName("wordcount")
    //2.将DStream按照五秒时间间隔划分一个个的micro batche数据(RDD序列)
    val ssc = new StreamingContext(conf,Seconds(5))
    //3.关闭Streaming默认的INFO级别的日志
    ssc.sparkContext.setLogLevel("ERROR")
    //4.创建DStream
    val stream = ssc.textFileStream("hdfs://Spark:9000/data")
    //5.使用DStream的高级算子
    stream.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print
    //6.启动流数据处理的应用
    ssc.start()
    //7.优雅关闭DStream的应用
    ssc.awaitTermination()
  }
}
