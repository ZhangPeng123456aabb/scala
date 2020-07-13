package com.baizhi

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDDDataSourceWithFileSystem {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("rdd create")
    val sc = new SparkContext(conf)
    //通过本地文件系统创建RDD
    //方式一
    val rdd:RDD[String] = sc.textFile("file:///i:\\data\\*.txt")
    //val rdd:RDD[String] = sc.textFile("hdfs://SparkOnYarn:9000/test.txt")
    rdd.flatMap(_.split(" ")).map((_,1)).groupBy(_._1).map(t=>(t._1,t._2.size)).sortBy(_._2,false,1).foreach(println)
    //方式二 Tuple2(文件的path,文件的内容)
    //val rdd:RDD[(String,String)] = sc.wholeTextFiles("file:///i:\\data\\*.txt")
    //rdd.flatMap(_._2.split("\r\n")).flatMap(_.split(" ")).map((_,1)).groupBy(_._1).map(t=>(t._1,t._2.size)).sortBy(_._2,false,1).foreach(println)
    //释放资源
    //println(rdd.getNumPartitions)
    sc.stop()
  }
}
