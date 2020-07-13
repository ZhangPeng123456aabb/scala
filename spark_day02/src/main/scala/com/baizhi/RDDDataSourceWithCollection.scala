package com.baizhi

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDDDataSourceWithCollection {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("rdd create")
    val sc = new SparkContext(conf)
    //方式一
    //1.通过scala的集合创建RDD
    //使用默认的分区数
    //val rdd:RDD[Int] = sc.makeRDD(List(1,2,3,4,5))
    //val rdd:RDD[Int] = sc.makeRDD(List(1,2,3,4,5),2)
    //方式二
    val rdd:RDD[Int] = sc.parallelize(List(1,2,3,4,5))
    val sum = rdd.reduce((e1,e2)=>e1+e2)
    println(s"$sum")
    println(rdd.getNumPartitions)
    sc.stop()
  }
}
