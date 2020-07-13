//package com.baizhi
//
//import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Put}
//import org.apache.hadoop.hbase.{HBaseConfiguration, HConstants, TableName}
//import org.apache.spark.{SparkConf, SparkContext}
//
//object SaveHBase1 {
//  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
//    val sc = new SparkContext(conf)
//    sc.textFile("hdfs://Spark:9000/test.txt")
//      .flatMap(_.split(" "))
//      .map((_,1))
//      .reduceByKey(_+_)
//      .foreachPartition(wordpairs=>{
//        //定义Hbase连接参数
////        val hconf = HBaseConfiguration.create()
////        hconf.set(HConstants.ZOOKEEPER_QUORUM,"hadoopnode00")
////        val conn = ConnectionFactory.createConnection(hconf)
//        val table = HBaseSink.conn.getTable(TableName.valueOf("baizhi:t_user"))
//        wordpairs.foreach(wordpair=>{
//          val put = new Put(wordpair._1.getBytes())
//          put.add("cf1".getBytes(),"key".getBytes(),wordpair._1.getBytes())
//          put.add("cf1".getBytes(),"count".getBytes(),(wordpair._2+"").getBytes())
//          table.put(put)
//        })
//        //conn.close()
//        table.close()
//      })
//    sc.stop()
//  }
//}
//object HBaseSink {
//  def createConnection(): Connection = {
//    val hConf = HBaseConfiguration.create()
//    hConf.set(HConstants.ZOOKEEPER_QUORUM,"hadoopnode00")
//    ConnectionFactory.createConnection(hConf)
//  }
//  val conn:Connection=createConnection()
//  Runtime.getRuntime.addShutdownHook(new Thread(){
//    override def run(): Unit = {
//      println("=-===close=====")
//      conn.close()
//    }
//  })
//}
