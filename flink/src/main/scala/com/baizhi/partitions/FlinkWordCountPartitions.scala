package com.baizhi.partitions
import org.apache.flink.api.common.functions.Partitioner
import org.apache.flink.streaming.api.scala._
object FlinkWordCountPartitions {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream -细化
    val ds = fsEnv.socketTextStream("Spark",5555)
    //对数据做转换
    ds.flatMap(_.split("\\s+"))
      .map((_,1))
      .partitionCustom(new Partitioner[String] {
        override def partition(key: String,numPartition:Int): Int = {
          val p = (key.hashCode&Integer.MAX_VALUE)%numPartition
          println("key:"+key+",numPartition:"+numPartition+",p:"+p)
          p
        }
      },0)
      .print()
    fsEnv.execute("FlinkWordCountPartitions")
  }
}
