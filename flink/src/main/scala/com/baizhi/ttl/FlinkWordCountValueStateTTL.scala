package com.baizhi.ttl
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.common.state.{StateTtlConfig, ValueState, ValueStateDescriptor}
import org.apache.flink.api.common.time.Time
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FlinkWordCountValueStateTTL {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val ds = fsEnv.socketTextStream("Spark",5555)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .map(new RichMapFunction[(String,Int),(String,Int)] {
        var valueState:ValueState[Int]=_

        override def open(parameters: Configuration): Unit = {
         val vsd = new ValueStateDescriptor[Int]("wordcount",createTypeInformation[Int])
          val ttlConfig=StateTtlConfig
            //状态存活时间
            .newBuilder(Time.seconds(5))
            //更新时机
            .setUpdateType(StateTtlConfig.UpdateType.OnCreateAndWrite)
            //永不返回过期数据
            .setStateVisibility(StateTtlConfig.StateVisibility.NeverReturnExpired)
            //.cleanupFullSnapshot()
            .cleanupInBackground()
            //当RocksDB 累计合并1000条记录的时候，查询一次过期的记录，并且将过期的记录清理掉
            //.cleanupIncrementally(10,true)
            .build()
          //开启TTL特性
          vsd.enableTimeToLive(ttlConfig)
          valueState=getRuntimeContext.getState(vsd)


        }
        override def map(value: (String, Int)): (String, Int) = {
          var historyValue = valueState.value()
          if(historyValue==null){
            historyValue=0
          }
          //更新历史
          valueState.update(historyValue+value._2)
          (value._1,valueState.value())
        }
      })
      .print()
    fsEnv.execute("FlinkWordCountValueStateTTL")
  }
}
