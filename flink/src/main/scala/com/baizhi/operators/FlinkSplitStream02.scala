package com.baizhi.operators
import org.apache.flink.streaming.api.functions.ProcessFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.util.Collector
object FlinkSplitStream02 {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    val logStream = fsEnv.socketTextStream("Spark",5555)
    val errorTag = new OutputTag[String]("error")
    val ds = logStream.process(new ProcessFunction[String,String] {
      override def processElement(line: String, context: ProcessFunction[String, String]#Context, collector: Collector[String]): Unit = {
        if(line.startsWith("INFO")){
          collector.collect(line)
        }else{
          context.output(errorTag,line)//分支输出
        }
      }
    })
    ds.print("正常信息")
    ds.getSideOutput(errorTag).print("错误信息")
    fsEnv.execute("FlinkSplitStream02")
  }
}
