package com.baizhi.evictor
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.function.AllWindowFunction
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow
import org.apache.flink.util.Collector

object FlinkWorldCountsGlobalWindowsEvictor {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val ds = fsEnv.socketTextStream("Spark",5555)
    //对数据做转换
    ds.windowAll(GlobalWindows.create())
      .trigger(UserDefineCountTrigger.of(5))
      .evictor(new UserDefineErrorEvictor[GlobalWindow](true,"info"))
      .apply(new AllWindowFunction[String,String,GlobalWindow] {
        override def apply(window: GlobalWindow, input: Iterable[String], out: Collector[String]): Unit = {
          input.foreach(item=>out.collect(item))
        }
      })
      .print("窗口输出")
    fsEnv.execute("FlinkWorldCountsGlobalWindowsEvictor")
  }
}
