package com.baizhi

import org.apache.spark.{SparkConf, SparkContext}

object RRDCheckpoint {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("rdd cache demo")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("file:///i://data/*.txt")
    //设置检查点的存放path
    sc.setCheckpointDir("hdfs://Spark:9000/checkpoint")
    rdd.checkpoint()
    rdd.count()
    val start = System.currentTimeMillis()
    rdd.count()
   val end =  System.currentTimeMillis()
    println("使用checkpoint，计算耗时ms:"+(end-start))
    //清空cache
    val start2 = System.currentTimeMillis()
    rdd.count()
    val end2 = System.currentTimeMillis()
    println("不使用checkpoint，所耗时长ms:"+(end2-start2))
    sc.stop()
  }
}
