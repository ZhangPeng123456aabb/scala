package com.baizhi.SparkStructuredStreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.OutputMode

/**
  * spark structured streaming提供了一种简化的方式(SQL)对数据进行分析处理
  * 实时的单词计数
  * 数据分析引擎：
  * 1.构建datasource[socket 通过网络套接字模拟流数据nc]
  * 2.应用计算规则(实时)sql语句
  * 3.输出[console控制台]
  */
object SparkStructuredStreamingForWordCount {
  def main(args: Array[String]): Unit = {
  //构建SparkSession[结构化流的入口]
    val spark = SparkSession.builder().master("local[*]").appName("Spark Structured Streaming").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    //spark构件留数据源DataFrame
    val df = spark
      .readStream
      .format("socket") //数据源格式
      .option("host", "spark")
      .option("port", "4444")
      .load()
    import spark.implicits._
    val ds = df
      .as[String]
      .flatMap(_.split(" "))
      .map((_, 1))
    ds.createOrReplaceTempView("t_word")
    val wordcounts = spark.sql("select _1,count(_2) from t_word group by _1")
    //val wordcounts = spark.sql("select * from t_word")
    //输出计算结果
    wordcounts
      .writeStream
      .format("console")
      //.outputMode(OutputMode.Append())//聚合不能使用
      .outputMode(OutputMode.Complete())// 输出模式 【如果：聚合操作必须得使用Update或者complete】
      //.outputMode(OutputMode.Update())
      .start()
      .awaitTermination()
  }
}
