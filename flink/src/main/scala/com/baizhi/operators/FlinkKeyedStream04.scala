package com.baizhi.operators
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FlinkKeyedStream04 {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val logStream = fsEnv.socketTextStream("Spark",5555)
    //001 ZhangPeng 销售 10000
    logStream.map(_.split("\\s+"))
      .map(ts=>Employee(ts(0),ts(1),ts(2),ts(3).toDouble))
      .keyBy("dept")
      .maxBy("salary")
      .print("Employee")
    fsEnv.execute("FlinkKeyedStream04")
  }
}
case class Employee(id:String,name:String,dept:String,salary:Double)