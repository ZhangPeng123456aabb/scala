package com.baizhi.datasource

import java.util.Properties

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.flink.streaming.api.scala._
object FlinkWordCountsKafkaSource02 {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建配置参数
    val props = new Properties()
    props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"HadoopNode01:9092,HadoopNode02:9092,HadoopNode03:9092")
    props.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"g1")
    val flinkkafkaConsumer = new FlinkKafkaConsumer[(Int,Long,String,String,String)]("t9",new UserDefineKafkaSerializationSchema(),props)
    val ds = fsEnv.addSource(flinkkafkaConsumer)
    ds.print()
    fsEnv.execute("FlinkWordCountsKafkaSource02")
  }
}
