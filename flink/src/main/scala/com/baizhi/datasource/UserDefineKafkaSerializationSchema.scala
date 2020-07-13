package com.baizhi.datasource

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema
import org.apache.flink.util.StringUtils
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.flink.streaming.api.scala._
class UserDefineKafkaSerializationSchema extends KafkaDeserializationSchema[(Int,Long,String,String,String)]  {
  override def isEndOfStream(t: (Int, Long, String, String, String)): Boolean = {
    false
  }

  override def deserialize(r: ConsumerRecord[Array[Byte], Array[Byte]]): (Int, Long, String, String, String) = {
    if(r.key()==null){
      (r.partition(),r.offset(),r.topic(),"",new String(r.value()) )
    }else{
      (r.partition(),r.offset(),r.topic(),new String(r.key()),new String(r.value()))
    }
  }

  override def getProducedType: TypeInformation[(Int, Long, String, String, String)] = {
    createTypeInformation[(Int,Long,String,String,String)]
  }
}
