package com.baizhi.broadcast

import org.apache.flink.api.common.state.MapStateDescriptor
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction
import org.apache.flink.util.Collector

class UserfineBroadcastProcessFunction(msd:MapStateDescriptor[String,Double]) extends BroadcastProcessFunction[(String,String,String,Double),(String,Double),String] {
  override def processElement(in1: (String, String, String, Double), readOnlyContext: BroadcastProcessFunction[(String, String, String, Double), (String, Double), String]#ReadOnlyContext, collector: Collector[String]): Unit = {
    val readOnlyState = readOnlyContext.getBroadcastState(msd)
    var value=readOnlyState.get(in1._3)
    if(value==null){
      value=0
    }
    val res = in1._1+"\t"+in1._2+"\t"+(in1._4+value)
    collector.collect(res)
  }
  //尝试修改当前状态
  override def processBroadcastElement(in2: (String, Double), context: BroadcastProcessFunction[(String, String, String, Double), (String, Double), String]#Context, collector: Collector[String]): Unit = {
    val state = context.getBroadcastState(msd)
    state.put(in2._1,in2._2)
  }
}
