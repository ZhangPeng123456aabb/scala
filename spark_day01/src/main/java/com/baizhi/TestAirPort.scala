package com.baizhi

import com.sun.corba.se.impl.orbutil.graph.Graph
import com.sun.javafx.geom.Edge
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession


/**
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/2/19 - 15:09 
  */
object TestAirPort {
  def main(args: Array[String]): Unit = {
    private val spark: SparkSession = SparkSession.builder()
      .appName(this.getClass.getSimpleName)
      .master("local[*]")
      .getOrCreate()
    private val sc: SparkContext =spark.sparkContext

    //加载数据
    private val flights: RDD[Array[String]] =
    sc.textFile("E:\\谷歌下载文件存放地\\USA Flight Datset - Spark Tutorial - Edureka.csv")
      .mapPartitionsWithIndex((index, iter) =>
        if (index == 0) iter.drop(1) else iter)
      .map(_.split(","))
      .cache()
    //点集合
    private val airports: RDD[(Long, String)] =
    flights.flatMap(x => Array((x(5).toLong, x(6)), (x(7).toLong, x(8)))).distinct()


    //边集合
    private val lines: RDD[Edge[Int]] =
    flights.map(x => (x(5).toLong, x(7).toLong, x(16).toInt))
      .distinct().map(x => Edge(x._1, x._2, x._3))

    //构建图
    private val graph: Graph[String, Int] = Graph(airports, lines)
  }

}
