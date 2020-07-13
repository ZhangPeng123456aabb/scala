package com.baizhi.datasource
import org.apache.flink.streaming.api.scala._
object FlinkWordCountUserDefineSource {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val ds = fsEnv.addSource(new UserDefineParallelSourceFunction)
    ds.setParallelism(1)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .print()
    fsEnv.execute("FlinkWordCountUserDefineSource")
  }
}
