package com.baizhi.output

import java.sql.{Driver, DriverManager}

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object OutputMySql {
  def main(args: Array[String]): Unit = {
    //设置Dstreaming的程序的主入口
   val conf =  new SparkConf().setMaster("local[*]").setAppName("Output Mysql")
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("ERROR")
    val kafkaParams=Map[String,Object](
      (ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"HadoopNode01:9092,HadoopNode02:9092,HadoopNode03:9092"),
      (ConsumerConfig.GROUP_ID_CONFIG,"g1"),
      (ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,classOf[StringDeserializer]),
      (ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,classOf[StringDeserializer])
    )
    val stream=KafkaUtils
      .createDirectStream(ssc,LocationStrategies
      //一个Topic分区对应一个RD分区
          .PreferConsistent,ConsumerStrategies
        //从kafka拉取数据消费配置对象
          .Subscribe[String,String](List("spark"),kafkaParams))
          stream.map(record=>(record.key(),record.value())).flatMap(_._2.split(" ")).map((_, 1)).reduceByKey(_ + _).foreachRDD(rdd=>{
            rdd.foreachPartition(partitionOfRecords=>{
              classOf[Driver]
              val connection=DriverManager.getConnection("jdbc:mysql://hadoopnode00:3306/spark","root","123456")
              val selectSql: String = "select *  from t_word where word = ?"
              val updateSql = "update t_word set count = ? where word = ?"
              val insertSql = "insert t_word(word,count) values(?,1)"
              partitionOfRecords.foreach(record=>{
                val pstm = connection.prepareStatement(selectSql)
                pstm.setString(1,record._1)
                val rs = pstm.executeQuery()
                //word存在
                if(rs.next()){
                  val count = rs.getInt("count")
                  val updateStatement=connection.prepareStatement(updateSql)
                  updateStatement.setInt(1,count+record._2)
                  updateStatement.setString(2,record._1)
                  updateStatement.executeUpdate()
                }else{
                  val insertStatement=connection.prepareStatement(insertSql)
                  insertStatement.setString(1,record._1)
                  insertStatement.executeUpdate()
                }
              })
              connection.close()
            })
          })
    ssc.start()
    ssc.awaitTermination()
  }
}
