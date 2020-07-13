package com.baizhi.window

import java.sql.Timestamp
import java.text.SimpleDateFormat

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.OutputMode

/**
  * 基于事件窗口操作
  */
object SparkStructuredStreamingForLateDataHandling {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("window on event time").master("local[*]").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._
    val df = spark
      .readStream
      .format("socket")
      .option("host", "spark")
      .option("port", "5555")
      .load()
    //发送数据时，word,timestamp
    //Hello,1573525440000 --->10:24:00
    //导入隐式转换的函数
    import org.apache.spark.sql.functions._
    //时：分：秒
    val sdf = new SimpleDateFormat("HH:mm:ss")
    df
      .as[String]
      .map(_.split(","))
      .map(arr =>(arr(0),new Timestamp(arr(1).toLong)))
      .toDF("word","timestamp")
      //水位线操作
      .withWatermark("timestamp","2 seconds")//vm = maxEventTime - 2
      //先根据时间时间划分窗口，再对窗口内的单词进一步分组
      //窗口长度5s 滑动3s
      .groupBy(window($"timestamp","5 seconds","3 seconds"),$"word")
       //统计窗口内单词出现的次数
      .count()

    /**
      * root
      *|-- window: struct (nullable = true)
      * |    |-- start: timestamp (nullable = true)
      * |    |-- end: timestamp (nullable = true)
      * |-- word: string (nullable = true)
      * |-- count: long (nullable = false)
      */
      .map(row=>(sdf.format(row.getStruct(0).getTimestamp(0)),sdf.format(row.getStruct(0).getTimestamp(1)),row.getString(1),row.getLong(2)))
      .toDF("startTime","endTime","word","num")
      .writeStream
      .format("console")
      //输出模式只能是append和update
      .outputMode(OutputMode.Update())
      .start()
      .awaitTermination()
  }
}
