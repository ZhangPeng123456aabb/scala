//package com.baizhi
//
//import java.sql.DriverManager
//
//import org.apache.spark.rdd.RDD
//import org.apache.spark.{SparkConf, SparkContext}
//
//object RDDSaveResult {
//  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf().setAppName("transformation test").setMaster("local[*]")
//    val sc = new SparkContext(conf)
//    var rdd:RDD[String]=sc.parallelize(List("Hello Hadoop","Hello Kafka","Hello Scala"),2)
//    //errorMethod(rdd)
//    //okMethod(rdd)
//    updateMethod(rdd)
//    sc.stop()
//  }
//
//  /**
//    * foreach 行动实际上是运行计算节点上的JVM容器(Executor),而连接对象是Driver创建的对象，不能跨虚拟机调用插入一行记录
//    * JAVA中连接的对象是不允许序列化网络传输，连接对象只能操作当前虚拟机中的数据
//    */
//  def errorMethod(rdd:RDD[String]): Unit ={
//    Class.forName("com.mysql.jdbc.Driver")
//    val connection=DriverManager.getConnection("jdbc:mysql://hadoopnode00:3306/spark","root","123456")
//    val sql:String="insert into t_word(word,num) values(?,?)"
//    val pstm = connection.prepareStatement(sql)
//    rdd.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).foreach(t=>{
//      pstm.setString(1,t._1)
//      pstm.setInt(2,t._2)
//      val count = pstm.executeUpdate()
//      println(s"插入了$count 行内容")
//    })
//    pstm.close()
//    connection.close()
//  }
//
//  /**
//    * foreach行动算子实际上是运行在计算节点JVM容器中的，而连接对象是Driver创建的对象，不能跨虚拟机插入一行记录
//    * JAVA中连接的对象是不允许序列化网络传输的，连接对象只能够操作当前虚拟机中的数据
//    */
//  /**
//    * 在foreach行动算子中，绑定写入JDBC插入数据的代码
//    * 问题：每处理一个元组都需要创建一个新的连接对象，成本较高
//    */
//  def okMethod(rdd:RDD[String]): Unit ={
//    rdd.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).foreach(t=>{
//      Class.forName("com.mysql.jdbc.Driver")
//      val connection=DriverManager.getConnection("jdbc:mysql://hadoopnode00:3306/spark","root","123456")
//      val sql="insert into t_word(word,num) values(?,?)"
//      val pstm = connection.prepareStatement(sql)
//      pstm.setString(1,t._1)
//      pstm.setInt(2,t._2)
//      val count = pstm.executeUpdate()
//      println(s"插入了$count 行内容")
//      pstm.close()
//      connection.close()
//    })
//  }
//
//  /**
//    * 优化升级版
//    * 保证foreach任务，每一个任务拥有一个连接对象
//    */
//  def updateMethod(rdd:RDD[String]): Unit ={
//    rdd.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).foreachPartition(iter=>{
//      Class.forName("com.mysql.jdbc.Driver")
//      val connection = DriverManager.getConnection("jdbc:mysql://hadoopnode00:3306/spark","root","123456")
//      val sql="insert into t_word(word,num) values(?,?)"
//      val pstm = connection.prepareStatement(sql)
//      while(iter.hasNext){
//        val tuple = iter.next()
//        val word = tuple._1
//        val num = tuple._2
//        pstm.setString(1,word)
//        pstm.setInt(2,num)
//        pstm.executeUpdate()
//      }
//      pstm.close()
//      connection.close()
//    })
//  }
//  def hbase(rdd:RDD[String]): Unit ={
//    rdd.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).foreach(t=>{
//      val conf = HBaseConfiguration.create
//      conf.set(HConstants.ZOOKEEPER_QUORUM,"hadoopnode00")
//
//     // val table = conn.getTable(TableName.valueOf("baizhi:t_wordcount"))
//    })
//  }
//}
