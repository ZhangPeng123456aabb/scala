package com.baizhi.operators

import java.{lang, util}

import org.apache.flink.streaming.api.collector.selector.OutputSelector
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FlinkSplitStream01 {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val ds = fsEnv.socketTextStream("Spark",5555)
    val splitStream = ds.split(new OutputSelector[String] {
      override def select(out: String): lang.Iterable[String] = {
        if(out.startsWith("INFO")){
          val array = new util.ArrayList[String]()
          array.add("info")
          return array
        }else{
          val array =new util.ArrayList[String]()
          array.add("error")
          return array
        }
      }
    })
    splitStream.select("info").print("info")
    splitStream.select("error").printToErr("error")
    fsEnv.execute("FlinkSplitStream01")
  }
}
