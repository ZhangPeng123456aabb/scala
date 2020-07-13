package com.baizhi.SparkStructuredStreaming

import java.sql.Timestamp

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.types.StructType

object StrucredStreamingWindowOperation {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("StructuredNetWorkdCount")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val spark = SparkSession.builder().getOrCreate()
    import spark.implicits._
    //构建DataFrame
    val df = spark.readStream
      .format("socket")
      .option("host", "spark")
      .option("port", "4444")
      .option("includeTimestamp", true)
      .load()
    val words = df.as[(String, Timestamp)]
      .flatMap(line => line._1.split(" ")
        .map(word => (word, line._2)))
      .toDF("word", "timestamp")
    words.createOrReplaceTempView("t_word")
    //val wordCounts = spark.sql("select * from t_word")
    //val wordCounts = words.groupBy(window($"timestamp","10 seconds","5 seconds"),$"word").count()
    //val wordCounts = words.withWatermark("timestamp","10 minutes")
      //  .groupBy(window($"timestamp","10 minutes","5 minutes"),$"word").count()
   // wordCounts.writeStream.outputMode(OutputMode.Complete()).format("console").start().awaitTermination()
    //wordCounts.writeStream.outputMode(OutputMode.Append()).format("console").start().awaitTermination()
    val df1 = spark.read.format("json").load("file:///F:\\study\\IDEA_project\\Scala\\spark_day09\\src\\main\\resources")
    val df2 = spark.readStream
      .format("json")
      .schema(new StructType().add("id","integer").add("name","String").add("salary","double"))
      .json("file:///F:\\study\\IDEA_project\\Scala\\spark_day09\\src\\main\\resources")

    df2.join(df1,Seq("id","id"),"leftOuter")
      .writeStream
      .format("console")
      .outputMode("append")
      .start()
      .awaitTermination()

  }
}
