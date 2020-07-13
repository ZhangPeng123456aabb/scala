package com.baizhi

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.scalalang.typed

/**
  * ds:强类型操作
  * spark context :rdd
  * spark streaming context :Streaming
  * spark session :sql
  */
object DataSetWithTypedOpt {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("typed opt").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._
    import org.apache.spark.sql._
    val ds = spark.sparkContext.makeRDD(List("Hello Hadoop", "Hello Scala"))
      .flatMap(_.split(" "))
      .map((_, 1))
      .toDS
    ds
      .groupByKey(t=>t._1)//根据单词进行分组
      .agg(typed.sum[(String,Int)](t=>t._2))//对单词初始值进行聚合
      .withColumnRenamed("TypedSumDouble(scala.Tuple2)","num")
      .show()
    spark.stop()
  }
}
