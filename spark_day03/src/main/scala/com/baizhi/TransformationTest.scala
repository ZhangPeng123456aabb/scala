package com.baizhi

import org.apache.spark.{SparkConf, SparkContext}

object TransformationTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("transformation test").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd = sc.makeRDD(List(1,2,3),2)
    //对每一个分区，应用匿名函数的内容
//    rdd.mapPartitions(i1=>{
//      val listBuffer = scala.collection.mutable.ListBuffer[(Int,Int)]()
//      while(i1.hasNext){
//        val elem = i1.next()
//        listBuffer.+=((elem,1))
//      }
//      listBuffer.iterator
//    }).foreach(println)
    //===========================2================================
    //对每一个分区，应用匿名函数的内容，并且为每一个分区提供一个下标index
//    rdd
//        .mapPartitionsWithIndex((index,i1)=>{
//          val listBuffer = scala.collection.mutable.ListBuffer[(Int,Int)]()
//          while(i1.hasNext){
//            val elem = i1.next
//            listBuffer.+=((elem,index))
//          }
//          listBuffer.iterator
//        }).foreach(println)
    //================================3===========================
    //Sample数据采样
    //参数一:true 允许重复数据   false不允许   参数二:概率
    //rdd.sample(true,0.50).foreach(println)
    //rdd.sample(false,0.60).foreach(println)
    //=================================4=============================
    //union对源RDD和参数RDD组合返回一个新的RDD
    //val rdd2 = sc.makeRDD(List(5,6,7,8,9))
    //rdd.union(rdd2).foreach(println)
    //==================================5=============================
    //intersection对源RDD和参数RDD求交集
//    val rdd2 = sc.makeRDD(List(5,6,7,8,9))
//    rdd.intersection(rdd2).foreach(println)
    //===================================6============================
    //distinct 数据去重 宽依赖
//    val rdd3 = sc.makeRDD(List(5,5,6,6,7,7,8,8,9,9,10,11,12))
//    rdd3.distinct(3).foreach(println)
    //===================================7=============================
//    val rdd4 = sc.makeRDD(List("Hello Hadoop","Hello Kafka"),2)
//    val rdd5 = rdd4.flatMap(_.split(" ")).map((_,1)).groupByKey(3)
//    rdd5.foreach(t=>println(t._1+"\t"+t._2.size))
//    println(rdd5.getNumPartitions)
    //====================================8================================
//    val rdd6 = sc.makeRDD(List("Hello Hadooop","Hello Kafka"),2)
//    rdd6.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).foreach(println)
    //====================================9==================================
//    val rdd6 = sc.makeRDD(List("Hello Hadoop","Hello Kafka"),2)
//    //柯里化函数第一个参数为初始值
//    rdd6.flatMap(_.split(" ")).map((_,1)).aggregateByKey(0)((agg,elem)=>agg+elem,(agg,elem)=>agg+elem).foreach(println)
    //====================================10====================================
    //sortByKey
    //val rdd7 = sc.makeRDD(List("Hello Hadoop","Hello kafka","Hello scala"),2)
    //根据key进行排序
    //rdd7.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).sortByKey(numPartitions = 1).foreach(println)
    //=====================================11=======================================
    //join 和 leftOuterJoin
    //join的两个RDD必须的是Tuple2
//    val rdd8=sc.parallelize(Array(("1",("zs",true)),("2",("ls","false")),("3",("ww",true))))
//    val rdd9 = sc.parallelize(Array(("1",("bj","a@sina.com")),("2",("sh","b@baidu.com"))))
//    rdd8.join(rdd9).foreach(t=>{
//      println(t._1+"\t"+t._2._1._1+"\t"+t._2._1._2+"\t"+t._2._2._1+"\t"+t._2._2._2)
//    })
//    rdd8.leftOuterJoin(rdd9).foreach(println)
    //=====================================12===================================
    //cogroup在类型为(K,V)和(K.W)的RDD上调用，返回一个(k,(Iterable<V>))类型的RDD
//    val rdd8 = sc.parallelize(Array(("zs","true"),("ww","false"),("zs","true")))
//    val rdd9 = sc.parallelize(Array(("zs","18"),("ww","16")))
//    rdd8.cogroup(rdd9).foreach(println)
    //结果
//    (ww,(CompactBuffer(false),CompactBuffer(16)))
//    (zs,(CompactBuffer(true, true),CompactBuffer(18)))
    //=======================================13==============================
    //重新分区 第一个参数是要分多少区，第二个参数是否shuffle 默认false  少分区变多分区true 多分区变少分区为false
    //rdd.coalesce(3,true).foreach(println)
    //或可以跟在所有的转换算子之后 手动重新分区的方法  shuffle
    //rdd.map(_*2).repartition(3).foreach(println)
    //========================================14===============================
    val rdd10 = sc.parallelize(List(5,6,7,8))
    //返回前rdd元素不在后rdd的rdd的rdd
    rdd.subtract(rdd10).foreach(println)
    sc.stop()
  }
}
