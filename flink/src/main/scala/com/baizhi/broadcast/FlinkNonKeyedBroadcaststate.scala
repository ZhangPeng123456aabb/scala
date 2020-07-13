package com.baizhi.broadcast
import org.apache.flink.api.common.state.MapStateDescriptor
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FlinkNonKeyedBroadcaststate {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    //001 zhangsan 销售 15000
    val userDataStream = fsEnv.socketTextStream("Spark",5555)
      .map(line=>line.split("\\s+"))
      .map(ts=>(ts(0),ts(1),ts(2),ts(3).toDouble))

    val msd = new MapStateDescriptor[String,Double]("rule",createTypeInformation[String],createTypeInformation[Double])
    //销售 5000
    //市场 -100
    val broadcastStream = fsEnv.socketTextStream("Spark",6666)
      .map(line=>line.split("\\s+"))
      .map(ts=>(ts(0),ts(1).toDouble))
      .broadcast(msd)

    userDataStream.connect(broadcastStream)
      .process(new UserfineBroadcastProcessFunction(msd))
      .print()

    fsEnv.execute("FlinkNonKeyedBroadcaststate")
  }
}
