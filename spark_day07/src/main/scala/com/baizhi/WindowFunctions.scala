package com.baizhi

import org.apache.spark.sql.SparkSession

object WindowFunctions {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("WindowFunctions Test").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._
    import org.apache.spark.sql.functions._
    //===============================join======================
//    val userInfoDF = spark.sparkContext.makeRDD(List((1,"zs"),(2,"ls"),(3,"ww"))).toDF("id","name")
//    val orderDF = spark.sparkContext.makeRDD(List((1,"iphone",1000,1),(2,"mi9",999,1),(3,"连衣裙",99,2))).toDF("oid","product","price","uid")
//    //join Df连接操作
//    userInfoDF
//        .join(orderDF,$"id"===$"uid","inner")
//        .show()
    //=================================cube===========================
//    List(
//      (110,50,80,80),
//      (120,60,95,75),
//      (120,50,96,70)).toDF("height","weight","IQ","EQ")
//        .cube($"height",$"weight")
//    //spark sql尝试操作根据元组第一个值和第二个值 进行各种操作，这种操作的好处，如果以后有任何第一个和第二个值的分区操作，都将出现cube的结果表中
//        .agg(avg("IQ"),avg("EQ")).show()
    //==================================DataSet==========================
    spark.stop()
  }
}
