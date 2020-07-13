//package com.baizhi
//
//import org.apache.hadoop.hbase.{HBaseConfiguration, HConstants, TableName}
//import org.apache.hadoop.hbase.client.{ConnectionFactory, Put}
//import org.apache.spark.{SparkConf, SparkContext}
//
//object SaveHbase {
//  def main(args: Array[String]): Unit = {
//    val sparkConf = new SparkConf()
//      .setAppName("wordcount")
//      .setMaster("local[*]")
//    val sc = new SparkContext(sparkConf)
//    sc.textFile("hdfs://Spark:9000/test.txt")
//      .flatMap(_.split(" "))
//      .map((_,1))
//      .reduceByKey(_+_)
//      .foreach(wordpair=>{
//        //定义Hbase连接参数
//        val hConf = HBaseConfiguration.create()
//        hConf.set(HConstants.ZOOKEEPER_QUORUM,"hadoopnode00")
//        val conn = ConnectionFactory.createConnection(hConf)
//        val table = conn.getTable(TableName.valueOf("baizhi:t_user"))
//        //写入到HBaase
//        val put = new Put(wordpair._1.getBytes())
//        put.add("cf1".getBytes(),"key".getBytes(),wordpair._1.getBytes())
//        put.add("cf1".getBytes(),"count".getBytes(),(wordpair._2+"").getBytes())
//        table.put(put)
//        conn.close()
//        table.close()
//      })
//    sc.stop()
//  }
//}
