package com.baizhi.operators

import org.apache.flink.streaming.api.functions.co.CoFlatMapFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.util.Collector

object FlinkConnectedStream02 {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val s1 = fsEnv.socketTextStream("Spark",5555)
    val s2 = fsEnv.socketTextStream("Spark",6666)

    s1.connect(s2).flatMap(new CoFlatMapFunction[String,String,String] {
      override def flatMap1(in1: String, collector: Collector[String]): Unit = {
        in1.split("\\s+").foreach(word=>collector.collect(word))
      }

      override def flatMap2(in2: String, collector: Collector[String]): Unit = {
        in2.split("\\s+").foreach(word=>collector.collect(word))
      }
    })
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .print()
    fsEnv.execute("FlinkConnectedStream02")
  }
}
