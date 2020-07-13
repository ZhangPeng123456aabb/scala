package com.baizhi.windows
import java.text.SimpleDateFormat

import org.apache.flink.api.common.functions.ReduceFunction
import org.apache.flink.api.common.state.ReducingStateDescriptor
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

object FlinkWordCountsTumblingTimeWindowsProcessWindowFunction03 {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val ds = fsEnv.socketTextStream("Spark",6666)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(_._1)
      .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
      .reduce((v1:(String,Int),v2:(String,Int))=>(v1._1,v1._2+v2._2),new ProcessWindowFunction[(String,Int),String,String,TimeWindow] {
        var windowStateDescriptor:ReducingStateDescriptor[Int]=_
        var gloabalStateDescriptor:ReducingStateDescriptor[Int]=_

        override def open(parameters: Configuration): Unit = {
          windowStateDescriptor=new ReducingStateDescriptor[Int]("wcs",new ReduceFunction[Int] {
            override def reduce(t: Int, t1: Int): Int =t+t1
          },createTypeInformation[Int])
          gloabalStateDescriptor =new ReducingStateDescriptor[Int]("gcs",new ReduceFunction[Int] {
            override def reduce(t: Int, t1: Int): Int = t+t1
          },createTypeInformation[Int])
        }
        override def process(key: String,
                             context: Context,
                             elements: Iterable[(String, Int)],
                             out: Collector[String]): Unit = {
          val w = context.window
          val sdf = new SimpleDateFormat("HH:mm:ss")
          val windowState = context.windowState.getReducingState(windowStateDescriptor)
          val globalState = context.globalState.getReducingState(gloabalStateDescriptor)

          elements.foreach(t=>{
            windowState.add(t._2)
            globalState.add(t._2)
          })
          out.collect(key+"\t"+windowState.get()+"\t"+globalState.get())
        }
      })
      .print()
    fsEnv.execute("FlinkWordCountsTumblingTimeWindowsProcessWindowFunction03")
  }
}
