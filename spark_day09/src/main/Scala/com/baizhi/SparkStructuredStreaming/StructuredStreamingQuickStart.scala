package com.baizhi.SparkStructuredStreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object StructuredStreamingQuickStart {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("StructuredNetworkWordCount")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val spark = SparkSession.builder().getOrCreate()
    import spark.implicits._
    //构建DataFrame
    val df = spark.readStream.format("socket").option("host","spark").option("port","4444").load()
    //转换为Dataset 进行算子操作
    val words = df.as[String].flatMap(_.split(" "))
    val wordCounts = words.groupBy("value").count()
    val query = wordCounts.writeStream.outputMode("complete").format("console").start()
    query.awaitTermination()
  }
}
