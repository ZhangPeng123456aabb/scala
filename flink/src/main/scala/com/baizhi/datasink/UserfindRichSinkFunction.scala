package com.baizhi.datasink

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}

class UserfindRichSinkFunction extends RichSinkFunction[(String,Int)] {
  override def open(parameters: Configuration): Unit = {
    println("open")
  }

  override def close(): Unit = {
    println("close")
  }

  override def invoke(value: (String, Int), context: SinkFunction.Context[_]): Unit = {
    println("insert into xxx"+value)
  }
}
