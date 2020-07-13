package com.baizhi.operatorstate
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, SourceFunction}
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import java.lang.{Long => JLong}
import java.util
import java.util.Collections
import scala.collection.JavaConverters._
import org.apache.flink.streaming.api.checkpoint.ListCheckpointed
object FlinkListCheckpointed {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val ds = fsEnv.addSource(new UserDefineCounterSource)
    ds.map(counter => "offset："+counter)
      .print()
    fsEnv.execute("FlinkListCheckpointed")
  }
}
class UserDefineCounterSource extends  RichParallelSourceFunction[Long] with ListCheckpointed[JLong]{
  @volatile
  private var isRunning =true
  private var offset =0L
  override def snapshotState(checkpointId: Long, timestamp: Long): util.List[JLong] = {
    println("存储:"+offset)
    //存储的是 offset
    Collections.singletonList(offset)
  }

  override def restoreState(list: util.List[JLong]): Unit = {
    for(i <- list.asScala){
      println("状态恢复"+i)
      offset=i
    }
  }

  override def run(ctx: SourceFunction.SourceContext[Long]): Unit = {
    val lock = ctx.getCheckpointLock
    while(isRunning){
      Thread.sleep(1000)
      lock.synchronized({
        //向下游输出offset
        ctx.collect(offset)
        offset += 1
      })
    }
  }

  override def cancel(): Unit = isRunning=false
}