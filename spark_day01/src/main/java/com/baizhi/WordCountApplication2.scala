package com.baizhi

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 开发spark应用
  */
object WordCountApplication2 {
  def main(args: Array[String]): Unit = {
    //初始化SparkConf和SparkContext
    val conf = new SparkConf().setAppName("wordCount").setMaster("local[*]")
    val sc = new SparkContext(conf)
    //进行大数据集的批处理
    sc
      .textFile("hdfs://Spark:9000/test.txt")
      .flatMap(_.split(" "))
      .map((_,1))
      .groupBy(_._1)
      .map(t=>(t._1,t._2.size))
      .sortBy(_._2,false,1)//根据单词出现的次数降序排列
      .saveAsTextFile("hdfs://Spark:9000/result3")
      //释放资源
      sc.stop()
  }
}
