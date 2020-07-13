package com.baizhi.datasink
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.redis.RedisSink
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig

object UserDefineRedisMapperSink {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val conf = new FlinkJedisPoolConfig.Builder()
      .setHost("Spark")
      .setPort(6379).build()
    //创建DataStream 细化
    val ds = fsEnv.socketTextStream("Spark",5555)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .addSink(new RedisSink(conf,new UserDefineRedisMapper))
    fsEnv.execute("UserDefineRedisMapperSink")
  }
}
