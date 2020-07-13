package com.baizhi.sql.datasource

import org.apache.spark.sql.SparkSession

object CreateDataframeTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("create dataSet").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    //    //通过JSON创建
//    val df = spark.read.json("file:///F:\\study\\IDEA_project\\Scala\\spark_day06\\src\\main\\resources")
//    df.printSchema()
//    df.show()
//    spark.stop()
//    val rdd = spark.sparkContext.makeRDD(List((1, "zs"), (2, "ls")))
//    val df = rdd.toDF("id","name")
//    df.show()
//
    val rdd = spark.sparkContext.makeRDD(List((1,"zs"),(2,"ls")))
    val df = rdd.toDF("id","name")
    //df.printSchema()
    df.show()
    spark.stop()
  }
}
case class User2(id:Int,name:String,sex:Boolean)