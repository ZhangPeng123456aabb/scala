package com.baizhi.join
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.co.ProcessJoinFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.util.Collector

object FlinkWordCountsIntervalJoin {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //设置时间特性
    fsEnv.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    //设置水位线计算频率
    fsEnv.getConfig.setAutoWatermarkInterval(1000)
    //001 zhangsan 时间戳
    val userKeyedStream = fsEnv.socketTextStream("Spark",5555)
      .map(_.split("\\s+"))
      .map(ts=>(ts(0),ts(1),ts(2).toLong))
      .assignTimestampsAndWatermarks(new UserAssignerWithPunctuatedWatermarks)
      .keyBy(t=>t._1)
    //001 100.0 时间戳
    val orderStream = fsEnv.socketTextStream("Spark",6666)
      .map(_.split("\\s+"))
      .map(ts=>(ts(0),ts(1).toDouble,ts(2).toLong))
      .assignTimestampsAndWatermarks(new OrderAssignerWithPunctuatedWatermarks)
      .keyBy(t=>t._1)

    userKeyedStream.intervalJoin(orderStream)
      .between(Time.seconds(-2),Time.seconds(2))
      .process(new ProcessJoinFunction[(String,String,Long),(String,Double,Long),String] {
        override def processElement(left: (String, String, Long),
                                    right: (String, Double, Long),
                                    context: ProcessJoinFunction[(String, String, Long), (String, Double, Long), String]#Context,
                                    out: Collector[String]): Unit = {
          val leftTimestamp = context.getLeftTimestamp
          val rightTimestamp = context.getRightTimestamp
          val timestamp = context.getTimestamp
          print(s"left:${leftTimestamp},right:${rightTimestamp},timestamp:${timestamp}")
          out.collect(left._1+"\t"+left._2+"\t"+right._2)
        }
      })
      .print()
    fsEnv.execute("FlinkWordCountsIntervalJoin")
  }
}
