package streaming

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object InputDataSourceKafka {
  def main(args: Array[String]): Unit = {
    //设置DStreaming的程序的主入口
    val conf = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    //每隔五秒对RDD数据进行划分
    val ssc = new StreamingContext(conf,Seconds(5))
    //设置日志等级
    ssc.sparkContext.setLogLevel("WARN")
    val kafkaParams=Map[String,Object](
      (ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"HadoopNode01:9092,HadoopNode02:9092,HadoopNode03:9092"),
      (ConsumerConfig.GROUP_ID_CONFIG,"g1"),
      (ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,classOf[StringDeserializer]),
      (ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,classOf[StringDeserializer])
    )

    val stream=KafkaUtils
      .createDirectStream(ssc,LocationStrategies
        //一个Topic分区对应一个RDD分区
        .PreferConsistent,ConsumerStrategies
        //从kafka拉取数据消费者配置对象
        .Subscribe[String,String](List("spark"),kafkaParams))
    stream.map(record=>(record.key(),record.value())).flatMap(_._2.split(" ")).map((_,1)).reduceByKey(_+_).print
    //启动流处理应用
    ssc.start()
    //优雅关闭DStreaming应用
    ssc.awaitTermination()
  }
}
