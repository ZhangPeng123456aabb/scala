package com.baizhi.SparkStructuredStreaming
import org.apache.spark.sql.{ForeachWriter, Row, SparkSession}
import org.apache.spark.sql.streaming.OutputMode
import redis.clients.jedis.Jedis

object SparkStructuredStreamingForOutputSink {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("input source").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._
    //=========================kafka重点=================================
    val df = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers","HadoopNode01:9092,HadoopNode02:9092,HadoopNode03:9092")
      //指定消费t9 topic的0号分区的earliest消费方式
      // 指定偏移量消费  In the json, -2 as an offset can be used to refer to earliest, -1 to latest.
      //0指定消费分区为0号分区
      .option("startingOffsets", """{"t10":{"0":-2}}""")
      .option("subscribe","t10")
      .load()
    //kafka record转换为要求的类型
  // val ds = df.selectExpr("CAST(key AS STRING)","CAST(value AS STRING)","CAST(topic AS STRING)","CAST(partition AS INT)","CAST(timestamp AS LONG)")
//      .as[(String,String,String,Int,Long)]
    // ds.createOrReplaceTempView("t_kafka")
   // val text = spark.sql("select key as k,value as v,topic as t,partition as p,timestamp as ts from t_kafka")
//    text
//      .writeStream
//      .format("console")
//      .outputMode(OutputMode.Append())
//      .start()
//      .awaitTermination()
    //========================文件[输出模式：支持Append]=============================
//    text
//      .writeStream
//      //文件格式为CSV,JSON,PARQUET,ORC等等
//      .format("json")
//      .outputMode(OutputMode.Append())
//      //检查点path,用以做故障恢复
//      .option("checkpointLocation","hdfs://Spark:9000/checkpoint1")
//      //path支持本地和HDFS文件系统路径
//      .option("path","file:///D:\\result")
//      .start()
//      .awaitTermination()
    //=================================CSV=====================================
//    text
//      .writeStream
//      .format("csv")
//      .outputMode(OutputMode.Append())
//      .option("checkpointLocation","hdfs://Spark:9000/checkpoint3")
//      .option("path","file:///d:\\result2")
//      .start()
//      .awaitTermination()
    //===================================kafka(输出模式)Append Update complete======================
//   val ds = df.selectExpr("CAST(key AS STRING)","CAST(value AS STRING)","CAST(topic AS STRING)","CAST(partition AS INT)","CAST(timestamp AS LONG)")
//   .as[(String,String,String,Int,Long)].flatMap(_._2.split(" ")).map((_,1))
//    ds.createOrReplaceTempView("t_kafka")
//    val text = spark.sql("select _1 as word,count(_2) as num from t_kafka group by _1")
//    text
//      //.selectExpr("CAST(k AS STRING)as key","CAST(v AS STRING) as value")
//      .selectExpr("CAST(word AS STRING) as key","CAST(num AS STRING) as value")
//      .writeStream
//      .format("kafka")
//      .outputMode(OutputMode.Update())
//      .option("checkpointLocation","hdfs://Spark:9000/checkpoint5")
//      .option("kafka.bootstrap.servers","HadoopNode01:9092,HadoopNode02:9092,HadoopNode03:9092")
//      .option("topic","t10")
//      .start()
//      .awaitTermination()
    //=================================Foreach【输出模式：Append | Updated | Completed】==========================
    val  ds = df.selectExpr("CAST(key AS STRING)","CAST(value AS STRING)","CAST(topic AS STRING)","CAST(partition AS INT)","CAST(timestamp AS LONG)")
    .as[(String,String,String,Int,Long)].flatMap(_._2.split(" ")).map((_,1))
    ds.createOrReplaceTempView("t_kafka")
    val text = spark.sql("select _1 as word,count(_2) as num from t_kafka group by _1")
    text
      .writeStream
      .outputMode(OutputMode.Update())
      .foreach(new ForeachWriter[Row] {
        /**
          * 打开方法
          * @param partitionId 分区序号
          * @param epochId
          * @return Boolean true 创建一个连接 对这一行数据进行处理操作
          *         false 不会创建链接 跳过这一行数据
          */
        override def open(partitionId: Long, epochId: Long): Boolean = true

        /**
          * 处理方法
          * @param value resultTable 行对象
          */
        override def process(value: Row): Unit = {
          val word = value.getString(0)
          val count = value.getLong(1).toString

          val jedis = new Jedis("Spark",6379)
          jedis.set(word,count)
          jedis.close()
        }

        override def close(errorOrNull: Throwable): Unit = if (errorOrNull != null)errorOrNull.printStackTrace()
      })
    //对resultTale中的每一行记录应用写出规则
      .option("checkpointLocation","hdfs://Spark:9000/checkpoint")
      .start()
      .awaitTermination()
  }
}
