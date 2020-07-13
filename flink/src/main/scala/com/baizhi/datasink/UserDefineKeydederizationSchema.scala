package com.baizhi.datasink

import org.apache.flink.streaming.util.serialization.KeyedSerializationSchema

class UserDefineKeyedSerializationSchema extends KeyedSerializationSchema[(String,Int)] {
  override def serializeKey(t: (String, Int)): Array[Byte] = {
    t._1.getBytes()
  }

  override def serializeValue(t: (String, Int)): Array[Byte] = {
    t._2.toString.getBytes()
  }

  override def getTargetTopic(t: (String, Int)): String = {
    "t9"
  }
}
