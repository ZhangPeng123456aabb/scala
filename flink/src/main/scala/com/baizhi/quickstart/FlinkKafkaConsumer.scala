package com.baizhi.quickstart

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
object FlinkKafkaConsumer {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(3)
    val props = new Properties()
    props.setProperty("bootstrap.servers","HadoopNode01:9092,HadoopNode02:9092,HadoopNode03:9092")
    props.setProperty("group.id","g1")
   val lines =  env.addSource(new FlinkKafkaConsumer("t9",new SimpleStringSchema(),props))
   lines.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(t=>t._1)
      .sum(1)
      .print()
    env.execute("FlinkKafkaConsumer")
  }
}
