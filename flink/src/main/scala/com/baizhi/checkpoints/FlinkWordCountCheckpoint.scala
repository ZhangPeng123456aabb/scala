package com.baizhi.checkpoints
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.environment.CheckpointConfig.ExternalizedCheckpointCleanup
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FlinkWordCountCheckpoint {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment

    // checkpoint 频率 12次/分钟
    fsEnv.enableCheckpointing(5000,CheckpointingMode.EXACTLY_ONCE)
    // 每次Checkpoint时长不得超过4s
    fsEnv.getCheckpointConfig.setCheckpointTimeout(4000)
    // 此次chk距离上一次chk时间不得少于2s，同一时刻只能有一个chk
    fsEnv.getCheckpointConfig.setMinPauseBetweenCheckpoints(2000);
    // 如果用取消任务，但是没有添加--withSavepoint，系统保留checkpoint数据
    fsEnv.getCheckpointConfig.enableExternalizedCheckpoints(ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
    // 如果检查点恢复失败，放弃任务执行
    fsEnv.getCheckpointConfig.setFailOnCheckpointingErrors(true);

    val dataStream: DataStream[String] = fsEnv.socketTextStream("Spark",6666)
    //3.对数据做转换
    dataStream.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .map(new RichMapFunction[(String,Int),(String,Int)] {
        var valueState:ValueState[Int]=_

        override def open(parameters: Configuration): Unit = {
          val vsd = new ValueStateDescriptor[Int]("wordcount",createTypeInformation[Int])
          valueState= getRuntimeContext.getState(vsd)
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

    fsEnv.execute("FlinkWordCountsQuickStart")
  }
}
