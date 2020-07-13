package com.baizhi.join
import java.text.SimpleDateFormat

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.AllWindowFunction
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

object FlinkWordCountsTumclingWindowJoin {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    fsEnv.setParallelism(1)
    //设置时间特性
    fsEnv.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    //设置水位线计算频率 1s
    fsEnv.getConfig.setAutoWatermarkInterval(1000)

    // 001 zhangsan 时间戳
    val userStrem: DataStream[(String,String,Long)] = fsEnv.socketTextStream("Spark",5555)
      .map(_.split("\\s+"))
      .map(ts=>(ts(0),ts(1),ts(2).toLong))
      .assignTimestampsAndWatermarks(new UserAssignerWithPunctuatedWatermarks)
    // 001 100.0 时间戳
    val orderStream: DataStream[(String,Double,Long)] = fsEnv.socketTextStream("Spark",6666)
      .map(_.split("\\s+"))
      .map(ts=>(ts(0),ts(1).toDouble,ts(2).toLong))
      .assignTimestampsAndWatermarks(new OrderAssignerWithPunctuatedWatermarks)

    userStrem.join(orderStream)
      .where(_._1)
      .equalTo(_._1)
      .window(TumblingEventTimeWindows.of(Time.seconds(2)))
      .apply((v1,v2,out:Collector[String])=>{
        out.collect(v1._1+"\t"+v1._2+"\t"+v2._2)
      })
      .print()
    fsEnv.execute("FlinkWordCountsQuickStart")
  }
}
