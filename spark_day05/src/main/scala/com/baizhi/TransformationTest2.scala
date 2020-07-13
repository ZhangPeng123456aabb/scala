package com.baizhi

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, State, StateSpec, StreamingContext}

/**
  * 测试Spark Streaming有状态操作算子 数据恢复
  */
object TransformationTest2 {
  def main(args: Array[String]): Unit = {
    //总结：SparkStreaming在进行状态恢复时，我们需要通过getOrCreate方式初始化ssc
    //参数一：指定用以进行故障恢复的checkpoint的path 参数二
    val ssc = StreamingContext.getOrCreate("hdfs://spark:9000/checkpoint4", createSSC)
    ssc.sparkContext.setLogLevel("ERROR")
    ssc.checkpoint("hdfs://Spark:9000/checkpoint5") // 窗口计算必须设定检查点目录
    // 总结：updateStateByKey 状态数据的全量更新  mapWithState对原始DStream增量输出
    //原因:只设定了状态数据的存放位置 并没有通过状态数据恢复程序
    //启动流处理的应用
    ssc.start()
    ssc.awaitTermination()
  }
  def createSSC():StreamingContext={
    val conf = new SparkConf().setMaster("local[*]").setAppName("transformation test")
    val ssc = new StreamingContext(conf,Seconds(5))
    ssc.sparkContext.setLogLevel("ERROR")
    ssc.checkpoint("hdfs://spark:9000/checkpoint")
    val dstream11 = ssc.socketTextStream("spark",9999)
    dstream11
      .flatMap(_.split(" "))
      .map((_,1))
      .mapWithState(StateSpec.function((k:String,v:Option[Int],state:State[Int])=>{
        var count=0
        //首先判断状态是否存在
        if(state.exists()){
          //在历史状态基础之上+1
          count=state.get()+v.get
        }else{
          //不存在 赋予初始值
          count = v.getOrElse(1)
        }
        //将最新的计算结果 更新到状态中
        state.update(count)
        (k,count)
      }))//增量更新 将状态更新后的DStream传递到下游处理
      .print()
      ssc
  }
}
