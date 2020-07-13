package com.baizhi.keyedstate
import org.apache.flink.api.common.functions.{AggregateFunction, RichMapFunction}
import org.apache.flink.api.common.state.{AggregatingState, AggregatingStateDescriptor, ListState, ListStateDescriptor}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import scala.collection.JavaConverters._

object FlinkUserPasswordsListState {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val ds = fsEnv.socketTextStream("Spark",5555)
    //对数据做转换 zhangsan 123456
    ds.map(_.split("\\s+"))
      .map(ts=>(ts(0),ts(1)))
      .keyBy(0)
      .map(new RichMapFunction[(String,String),String] {
        var historyPasswords:ListState[String]=_

        override def open(parameters: Configuration): Unit = {
          val lsd = new ListStateDescriptor[String]("pwdstate",createTypeInformation[String])
          historyPasswords=getRuntimeContext.getListState(lsd)
        }
        override def map(value: (String, String)): String = {
            var list = historyPasswords.get().asScala.toList
            list=list.::(value._2)
            list = list.distinct
          historyPasswords.update(list.asJava)
          value._1+"\t"+list.mkString(",")
        }
      })
      .print()
    fsEnv.execute("FlinkUserPasswordsListState")
  }
}
