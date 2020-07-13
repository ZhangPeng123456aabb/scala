package com.baizhi.datasink
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object HbaseSinDaemon {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val ds = fsEnv.socketTextStream("Spark",5555)
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .addSink(new HbaseFlinkFunction)
    fsEnv.execute("HbaseSinDaemon")
  }
}
