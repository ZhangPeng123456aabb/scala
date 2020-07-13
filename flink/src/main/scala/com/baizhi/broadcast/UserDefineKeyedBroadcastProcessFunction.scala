package com.baizhi.broadcast

import org.apache.flink.api.common.state.{MapStateDescriptor, ValueState, ValueStateDescriptor}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.co.KeyedBroadcastProcessFunction
import org.apache.flink.util.Collector
import org.apache.flink.streaming.api.scala._
class UserDefineKeyedBroadcastProcessFunction(msd:MapStateDescriptor[String,String]) extends KeyedBroadcastProcessFunction[String,(String,Int),(String,String),String] {
  var counts:ValueState[Int]=_

  override def open(parameters: Configuration): Unit = {
    println("open")
    val vsd = new ValueStateDescriptor[Int]("count",createTypeInformation[Int])
    counts = getRuntimeContext.getState(vsd)
  }
  override def processElement(in1: (String, Int), readOnlyContext: KeyedBroadcastProcessFunction[String, (String, Int), (String, String), String]#ReadOnlyContext, collector: Collector[String]): Unit = {
    var history=counts.value()
    if(history==null){
      history=0
    }
    counts.update(history+1)
    println(in1._1+" "+counts.value())
    val readOnlyState = readOnlyContext.getBroadcastState(msd)
    if(readOnlyState.get(in1._1)!=null){
      //次数：优惠金额
      var value = readOnlyState.get(in1._1)
      val threshold = value.split(":")(0).toInt
      if(counts.value() > threshold){
        println("满足推送条件")
        collector.collect(in1._1+"\t"+value.split(":")(1))
        //清除状态
        counts.clear()
      }else{
        println("不满足条件，当前是:"+counts.value()+"需要:"+threshold)
      }
    }
  }

  override def processBroadcastElement(in2: (String, String), context: KeyedBroadcastProcessFunction[String, (String, Int), (String, String), String]#Context, collector: Collector[String]): Unit = {
    val state = context.getBroadcastState(msd)
    //类别 次数：优惠金额
    state.put(in2._1,in2._2)
  }
}
