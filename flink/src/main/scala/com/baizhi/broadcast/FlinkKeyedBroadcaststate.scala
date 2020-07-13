package com.baizhi.broadcast
import org.apache.flink.api.common.state.MapStateDescriptor
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FlinkKeyedBroadcaststate {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    //001 zhangsan 手机通讯
    //002 zhangsan 母婴用品
    val ds = fsEnv.socketTextStream("Spark",5555)
      .map(line=>line.split("\\s+"))
      .map(ts=>(ts(2),1))
      .keyBy(0)

    val msd = new MapStateDescriptor[String,String]("rule",createTypeInformation[String],createTypeInformation[String])
    //母婴用品
    val broadcastStream = fsEnv.socketTextStream("Spark",6666)
      .map(line=>line.split("\\s+"))
      .map(ts=>(ts(0),ts(1)+":"+ts(2)))
      .broadcast(msd)

    ds.connect(broadcastStream)
      .process(new UserDefineKeyedBroadcastProcessFunction(msd))
      .print()

    fsEnv.execute("FlinkKeyedBroadcaststate")
  }
}
