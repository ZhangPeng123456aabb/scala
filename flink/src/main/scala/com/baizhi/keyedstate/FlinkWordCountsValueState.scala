package com.baizhi.keyedstate
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FlinkWordCountsValueState {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val ds = fsEnv.socketTextStream("Spark",5555)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .map(new RichMapFunction[(String,Int),(String,Int)] {
        var valueState:ValueState[Int]=_
        override def open(parameters: Configuration): Unit = {
          val vsd = new ValueStateDescriptor[Int]("wordcount", createTypeInformation[Int])
          valueState=getRuntimeContext.getState(vsd)
        }
    override def map(value:(String,Int)):(String,Int)= {
      var historyValue = valueState.value()
      if(historyValue==null){
        historyValue=0
      }
      //更新历史
      valueState.update(historyValue+value._2)
      (value._1,valueState.value())
      }
    })
      .print()
    fsEnv.execute("FlinkWordCountsValueState")
  }
}
