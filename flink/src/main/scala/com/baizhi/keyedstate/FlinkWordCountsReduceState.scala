package com.baizhi.keyedstate
import org.apache.flink.api.common.functions.{ReduceFunction, RichMapFunction}
import org.apache.flink.api.common.state.{ReducingState, ReducingStateDescriptor}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala._

object FlinkWordCountsReduceState {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val ds = fsEnv.socketTextStream("Spark",5555)
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .map(new RichMapFunction[(String,Int),(String,Int)]{
        var reduceState:ReducingState[Int]=_

        override def open(parameters: Configuration): Unit = {
          val rsd = new ReducingStateDescriptor[Int]("wordCount",new ReduceFunction[Int] {
            override def reduce(value1: Int, value2: Int): Int = value1+value2
          },createTypeInformation[Int])
          reduceState=getRuntimeContext.getReducingState(rsd)
        }
        override def map(value: (String, Int)): (String, Int) = {
          reduceState.add(value._2)
          (value._1,reduceState.get())
        }
      })
      .print()
    fsEnv.execute("FlinkWordCountsReduceState")
  }
}
