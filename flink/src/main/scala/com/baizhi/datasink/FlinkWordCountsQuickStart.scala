package com.baizhi.datasink

import java.time.ZoneId
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.fs.bucketing.{BucketingSink, DateTimeBucketer}

object FlinkWordCountsQuickStart {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val bucketSink = new BucketingSink[String]("hdfs://Spark:9000/bucketSink")
    bucketSink.setBucketer(new DateTimeBucketer[String]("yyyy-MM-dd-HH",ZoneId.of("Asia/Shanghai")))
    //对数据DataStream -细化
    val ds = fsEnv.socketTextStream("Spark",5555)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .map(t=>t._1+"\t"+t._2)
      .addSink(bucketSink)

    fsEnv.execute("FlinkWordCountsQuickStart")
  }
}
