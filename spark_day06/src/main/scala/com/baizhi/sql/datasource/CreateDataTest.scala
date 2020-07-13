package com.baizhi.sql.datasource

import org.apache.spark.sql.SparkSession
import org.spark_project.jetty.server.Authentication

/**
  * 测试Datase创建方法t
  */
object CreateDataTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("create dataset").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
//    //1.通过样例类创建dataset
//    val tupleList = List(User(1,"zs",true),User(2,"ls",false),User(3,"ww",true))
//    import spark.implicits._
//    val dataSet=tupleList.toDS()
//    dataSet.show()
//    spark.stop()
    //2.通过元组创建dataset
//    val tupleList=List((1,"zs",true),(2,"ls",false),(3,"ww",true))
//    import spark.implicits._
//    val dataset=tupleList.toDS()
//    dataset.show()
    //3.通过JSON创建
//    val dataset=spark.read.json("file:///F:\\study\\IDEA_project\\Scala\\spark_day06\\src\\main\\resources").as("user")
//    dataset.show()
    //4.通过RDD【元组集合】创建
//    val rdd = spark.sparkContext.makeRDD(List((1,"zs",true),(2,"ls",false)))
//    import spark.implicits._
//    val dataset = rdd.toDS()
//    dataset.printSchema()
//    dataset.show()
    val rdd = spark.sparkContext.makeRDD(List(User(1,"zs",true),User(2,"ls",false)))
    import spark.implicits._
    val dataSet = rdd.toDS()
    dataSet.show()
    spark.stop()
  }
}
case class User(id:Int,name:String,sex:Boolean)