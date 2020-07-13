package com.baizhi

import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, HConstants}
import org.apache.spark.{SparkConf, SparkContext}

object RDDDataSourceWithHBase {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("rdd create")
    val sc = new SparkContext(conf)
    //准备HBase配置对象
    val hbaseConfig = HBaseConfiguration.create()
    hbaseConfig.set(HConstants.ZOOKEEPER_QUORUM,"hadoopnode00")
    hbaseConfig.set(HConstants.ZOOKEEPER_CLIENT_PORT,"2181")
    hbaseConfig.set(TableInputFormat.INPUT_TABLE,"baizhi:t_user")//操作表明
    hbaseConfig.set(TableInputFormat.SCAN_COLUMNS,"cf1:name cf1:age cf1:sex")
    //通过sc操作hadoop方法创建RDD对象
    val rdd = sc.newAPIHadoopRDD(hbaseConfig,classOf[TableInputFormat],classOf[ImmutableBytesWritable],classOf[Result])
    rdd.foreach(t=>{
      val rowkey = Bytes.toString(t._1.get())
      val name = Bytes.toString((t._2.getValue(Bytes.toBytes("cf1"),Bytes.toBytes("name"))))
      val age = Bytes.toString(t._2.getValue(Bytes.toBytes("cf1"),Bytes.toBytes("age")))
      val sex = Bytes.toString(t._2.getValue(Bytes.toBytes("cf1"),Bytes.toBytes("sex")))
      println(s"$rowkey $name $age $sex")
    })
    sc.stop()
  }
}
