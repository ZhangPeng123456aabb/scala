package com.baizhi.datasink

import java.util.UUID

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Put}

class HbaseFlinkFunction extends RichSinkFunction[(String,Int)] {
  var connection:Connection=_

  override def open(parameters: Configuration): Unit = {
    super.open(parameters)
    val conn = HBaseConfiguration.create()
    conn.set("hbase.zookeeper.quorum", "Spark")
    conn.set("hbase.zookeeper.property.clientPort", "2181")
    connection = ConnectionFactory.createConnection(conn)
  }

  override def invoke(value: (String, Int), context: SinkFunction.Context[_]): Unit = {
  val tableName = TableName.valueOf("baizhi:t_user")
    val table = connection.getTable(tableName)
    val put = new Put(UUID.randomUUID().toString.getBytes())
    put.addColumn("cf1".getBytes,"word".getBytes,value._1.getBytes())
    put.addColumn("cf1".getBytes,"num".getBytes,value._2.toString.getBytes())
    table.put(put)
    table.close()
  }
  override def close(): Unit = {
    super.close()
    connection.close()
  }
}
