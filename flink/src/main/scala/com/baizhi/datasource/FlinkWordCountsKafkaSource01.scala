package com.baizhi.datasource

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.kafka.clients.consumer.ConsumerConfig
object FlinkWordCountsKafkaSource01 {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val props = new Properties()
    props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"HadoopNode01:9092,HadoopNode02:9092,HadoopNode03:9092")
    props.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"g1")
    val flinkkafkaConsumer = new FlinkKafkaConsumer[String]("t9",new SimpleStringSchema(),props)
    val ds = fsEnv.addSource(flinkkafkaConsumer)
    ds.setParallelism(10)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .print()
    fsEnv.execute("FlinkWordCountsKafkaSource01")
  }
}
