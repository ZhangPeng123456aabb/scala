package com.baizhi.datasink

import org.apache.flink.api.java.io.CsvOutputFormat
import org.apache.flink.api.java.tuple.Tuple2
import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.scala._
object FlinkDataSinkDileBased {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    fsEnv.setParallelism(10)
    val output = new CsvOutputFormat[Tuple2[String,Int]](new Path("file:///D:\\fink-results1"))
      //创建DataStream -细化
    val ds = fsEnv.socketTextStream("Spark",5555)
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .map(t=>new Tuple2(t._1,t._2))
      .writeUsingOutputFormat(output)
    fsEnv.execute("FlinkDataSinkDileBased")
  }
}
