package com.baizhi.datasink
import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

object write2HBaseWithRichSinkFunction {
  def main(args: Array[String]): Unit = {
    val topic = "t1"
    val props = new Properties()
    props.put("bootstrap.servers","Spark:9092")
    props.put("group.id","g1")
    props.put("enable.auto.commit","true")
    props.put("auto.commit.interval.ms","1000")
    props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    fsEnv.enableCheckpointing(5000)
    fsEnv.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    val myConsumer = new FlinkKafkaConsumer[String](topic,new SimpleStringSchema,props)
    val ds:DataStream[String] = fsEnv.addSource(myConsumer)
    //写入HBase
    ds.addSink(new HBaseWriter)
    fsEnv.execute("write2HBaseWithRichSinkFunction")
  }
}
