package com.baizhi.SparkStructuredStreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object StructuredStreamingSource {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("StructuredNetworkWordCount")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val spark = SparkSession.builder().getOrCreate()
    import spark.implicits._
    //========================CSV===============================
//    val df = spark.readStream.format("csv")
//      .schema(new StructType().add("id","integer").add("name","String").add("salary","double"))
//      .csv("file:///D:\\data")
    //==========================json==================================
//    val df = spark.readStream
//    .format("json")
//    .schema(new StructType().add("id","integer").add("name","String").add("salary","float"))
//    .json("file:///F:\\study\\IDEA_project\\Scala\\spark_day09\\src\\main\\resources")
//    df.createOrReplaceTempView("t_word")
//    spark.sql("select * from t_word")
//      .writeStream
//      .outputMode("append")
//      .format("console")
//      .start()
//      .awaitTermination()
    //============================kafka==========================
    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "HadoopNode01:9092,HadoopNode02:9092,HadoopNode03:9092")
      //指定消费者偏移量
      .option("subscribe", "t9")
      .load()
    val kafka = df.selectExpr("CAST(key As STRING)", "CAST(value As STRING)", "topic", "partition")
      .as[(String,String,String,Int)]
    kafka.createOrReplaceTempView("t_kafka")
//    spark.sql("select * from t_kafka")
    ////      .writeStream
    ////      .format("console")
    ////      .outputMode("append")
    ////      .start()
    ////      .awaitTermination()
    spark.sql("select count(*) from t_kafka group by partition")
      .writeStream
      .format("console")
      .outputMode("complete")
      .start()
      .awaitTermination()
  }
}
