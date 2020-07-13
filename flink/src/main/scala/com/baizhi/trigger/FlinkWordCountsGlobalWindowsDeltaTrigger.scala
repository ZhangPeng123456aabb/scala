package com.baizhi.trigger

import org.apache.flink.streaming.api.functions.windowing.delta.DeltaFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow
import org.apache.flink.util.Collector
object FlinkWordCountsGlobalWindowsDeltaTrigger {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val ds = fsEnv.socketTextStream("Spark",6666)
    var deltaTrigger = UserDefineDeltaTrigger.of[(String,Double),GlobalWindow](10.0,new DeltaFunction[(String, Double)] {
      override def getDelta(LastData: (String, Double), newData: (String, Double)): Double = {
        newData._2-LastData._2
      }
    },createTypeInformation[(String,Double)].createSerializer(fsEnv.getConfig))
    //对数据做转换 10
    // a 100.0
    ds.map(_.split("\\s+"))
      .map(ts=>(ts(0),ts(1).toDouble))
      .keyBy(_._1)
      .window(GlobalWindows.create)
      .trigger(deltaTrigger)
      .apply(new WindowFunction[(String,Double),(String,Double),String,GlobalWindow] {
        override def apply(key: String,
                           window: GlobalWindow,
                           input: Iterable[(String, Double)],
                           out: Collector[(String, Double)]): Unit = {
          println("key:"+key+"w:"+window)
          input.foreach(t=>println(t))
        }
      })
      .print()
    fsEnv.execute("FlinkWordCountsGlobalWindowsDeltaTrigger")
  }
}
