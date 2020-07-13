package com.baizhi.operatorstate

import org.apache.flink.api.common.state.{ListState, ListStateDescriptor}
import org.apache.flink.runtime.state.{FunctionInitializationContext, FunctionSnapshotContext}
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction
import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.streaming.api.scala._
import scala.collection.mutable.ListBuffer
import  scala.collection.JavaConverters._
class UserDefineBufferSink(threshold: Int = 0) extends SinkFunction[String] with CheckpointedFunction {
  @transient
  private var checkpointedState: ListState[String] = _
  private val bufferedElements = ListBuffer[String]()

  override def invoke(value: String): Unit = {
    bufferedElements += value
    println(bufferedElements.size+ " " + (bufferedElements.size >= threshold))
    if(bufferedElements.size == threshold){
      bufferedElements.foreach(item => println(item))
      bufferedElements.clear()
    }
  }
  //将数据保存起来
  override def snapshotState(functionSnapshotContext: FunctionSnapshotContext): Unit = {
    checkpointedState.clear()
    for(i <- bufferedElements){
      checkpointedState.add(i)
    }
  }

  override def initializeState(context: FunctionInitializationContext): Unit = {
    val descriptor = new ListStateDescriptor[String]("buffered-elements",createTypeInformation[String])
    checkpointedState = context.getOperatorStateStore.getListState(descriptor)
    if(context.isRestored){
      println("状态恢复中...")
      for(element <- checkpointedState.get().asScala) {
        bufferedElements += element
      }
    }
  }
}

