package com.baizhi.window

import java.sql.Timestamp
import java.text.SimpleDateFormat

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.OutputMode

object SparkStructuredStreamingForWindowOnEventTime {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("window on event time").master("local[*]").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    val df = spark
      .readStream
      .format("socket")
      .option("host", "Spark")
      .option("port", "4444")
      .load()

    // 发送数据时：word,timestamp
    // Hello,1573525440000  --> 10:24:00

    // 导入隐式转换的函数
    import org.apache.spark.sql.functions._

    val sdf = new SimpleDateFormat("HH:mm:ss") // 时:分:秒

    df
      .as[String]
      .map(_.split(",")) // Hello,1573525440000 --> Array("Hello","1573525440000")
      .map(arr => (arr(0), new Timestamp(arr(1).toLong))) //("Hello",new Timestamp(1573525440000))
      .toDF("word", "timestamp")
      // 先根据事件时间划分窗口，再对窗口内的单词进一步分组
      .groupBy(window($"timestamp", "5 seconds", "3 seconds"), $"word") // 滑动窗口 长度5s  滑动3s
      // 统计窗口内单词出现的次数
      .count()

      /**
        * root
        * |-- window: struct (nullable = true)
        * |    |-- start: timestamp (nullable = true)
        * |    |-- end: timestamp (nullable = true)
        * |-- word: string (nullable = true)
        * |-- count: long (nullable = false)
        */
      //.printSchema()
      .map(row => (sdf.format(row.getStruct(0).getTimestamp(0)),sdf.format(row.getStruct(0).getTimestamp(1)),row.getString(1),row.getLong(2)))
      .toDF("startTime","endTime","word","num")

      .writeStream
      .format("console")
      .outputMode(OutputMode.Complete())
      .start()
      .awaitTermination()
  }
}
