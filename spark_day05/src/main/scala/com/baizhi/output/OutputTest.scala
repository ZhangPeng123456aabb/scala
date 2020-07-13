package com.baizhi.output

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.clients.jedis.JedisPool

object OutputTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("windows").setMaster("local[*]")
    val ssc = new StreamingContext(conf, Seconds(1))
    ssc.sparkContext.setLogLevel("ERROR")
    val dstream = ssc.socketTextStream("spark", 8888)

    ssc.sparkContext.hadoopConfiguration.set("fs.defaultFS", "hdfs://Spark:9000") // HDFS集群地址

    dstream
      .flatMap(_.split(" "))
      .map((_, 1))
      //.window(Seconds(5))
      .window(Seconds(10), Seconds(10)) // 设定长度为10，滑动步长为5的窗口【跳跃滑动窗口】
      // 单个参数的window，严格意义上来说并不是翻滚窗口，本质跳跃窗口（长度为指定的length，滑动步长为默认值1s）
      .reduceByKey((v1: Int, v2: Int) => v1 + v2, 1)
      //.print()
    // -------------------------------------------------------------------------
     //.saveAsTextFiles("result","xxx") // 将计算结果保存到本地文件系统中 不能手动指定存放目录
    // -------------------------------------------------------------------------
     //.saveAsObjectFiles("result", "") // 将计算结果以序列化文件的形式 存放到项目所在目录
    // -------------------------------------------------------------------------
    //      .saveAsNewAPIHadoopFiles(
    //      "result",
    //      "",
    //      classOf[Text],
    //      classOf[IntWritable],
    //      classOf[TextOutputFormat[Text, IntWritable]])
    // -------------------------------------------------------------------------
        .foreachRDD(rdd=>{
      rdd.foreachPartition(iter=>{
        //创建连接对象 一个分区共享同一个连接对象
        val jedisPool = new JedisPool("Spark",6379)
            iter.foreach(t=>{
              val word=t._1
              val count = t._2
              val jedis = jedisPool.getResource
              jedis.set(word,count.toString)
              jedisPool.returnResource(jedis)
            })
        })
    })
      ssc.start()
      ssc.awaitTermination()
  }
}
