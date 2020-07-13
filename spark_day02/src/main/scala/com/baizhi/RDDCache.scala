package com.baizhi

import org.apache.spark.{SparkConf, SparkContext}

object RDDCache {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("rdd cache demo")
    val sc = new SparkContext(conf)
//    val rdd = sc.textFile("file:///i://data/*.txt")
//    //尝试对RDD进行cache
//    rdd.cache()
//    rdd.count()
//    val start = System.currentTimeMillis()
//    rdd.count()
//    val end =  System.currentTimeMillis()
//    println("使用缓存cache，计算耗时ms:"+(end-start))
//
//    //清空cache
//    rdd.unpersist()
//    val start2 = System.currentTimeMillis()
//    rdd.count()
//    val end2 = System.currentTimeMillis()
//    println("不使用缓存cache，计算耗时ms:"+(end2-start2))
    val rdd = sc.makeRDD(1 to 10, 2)
    rdd.saveAsTextFile("file:///D://result2")
    sc.stop()
  }
}
