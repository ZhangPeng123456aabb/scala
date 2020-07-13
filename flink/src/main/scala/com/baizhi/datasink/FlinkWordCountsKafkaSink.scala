package com.baizhi.datasink

import java.util.Properties

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.{ByteArraySerializer, IntegerSerializer, StringSerializer}

object FlinkWordCountsKafkaSink {


  def main(args: Array[String]): Unit = {
    //1.创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment

    val props = new Properties()
    props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"HadoopNode01:9092,HadoopNode02:9092,HadoopNode03:9092")
    //不建议覆盖
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,classOf[ByteArraySerializer])
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,classOf[ByteArraySerializer])


    props.put(ProducerConfig.RETRIES_CONFIG,"3")
    props.put(ProducerConfig.ACKS_CONFIG,"-1")
    props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,"true")
    props.put(ProducerConfig.BATCH_SIZE_CONFIG,"100")
    props.put(ProducerConfig.LINGER_MS_CONFIG,"500")


    //2.创建DataStream -细化
    val dataStream: DataStream[String] = fsEnv.socketTextStream("Spark",5555)
    //3.对数据做转换
    dataStream.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .addSink(new FlinkKafkaProducer[(String, Int)]("topicxx",
        new UserDefineKeyedSerializationSchema,
        props))

    fsEnv.execute("FlinkWordCountsQuickStart")
  }
}