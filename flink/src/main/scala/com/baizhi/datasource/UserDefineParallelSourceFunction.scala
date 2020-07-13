package com.baizhi.datasource
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.source.{ParallelSourceFunction, RichParallelSourceFunction, SourceFunction}
import scala.util.Random
class UserDefineParallelSourceFunction extends ParallelSourceFunction[String] {
  val lines=Array("this is a demo","hello world","hello flink")
  @volatile
  var isRunning=true
  //运行
  override def run(sourceContext: SourceFunction.SourceContext[String]): Unit = {
    while(isRunning){
      Thread.sleep(1000)
      sourceContext.collect(lines(new Random().nextInt(lines.length)))
    }
  }
  //取消
  override def cancel(): Unit = {
    isRunning=false
  }
}
