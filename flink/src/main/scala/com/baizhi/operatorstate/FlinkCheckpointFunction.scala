package com.baizhi.operatorstate
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
object FlinkCheckpointFunction {
  def main(args: Array[String]): Unit = {
      //1.创建StreamExecutionEnvironment
      val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
      fsEnv.setParallelism(1)
      //2.创建DataStream -细化
      val dataStream: DataStream[String] = fsEnv.socketTextStream("Spark",5555)

      //3.对数据做转换
      dataStream.map(line => line +" baizhi edu")
        .addSink(new UserDefineBufferSink(5))
      fsEnv.execute("FlinkWordCountsQuickStart")
  }
}
