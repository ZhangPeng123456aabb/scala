package com.baizhi.datasource

import org.apache.flink.api.common.io.FilePathFilter
import org.apache.flink.api.java.io.TextInputFormat
import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.functions.source.FileProcessingMode
import org.apache.flink.streaming.api.scala._
object FlinkWordCountsFileSource03 {
  def main(args: Array[String]): Unit = {
    //创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //创建DataStream 细化
    val FilePath="file:///I:\\data"
    val inputFormat = new TextInputFormat(null)
    inputFormat.setFilesFilter(new FilePathFilter {
      override def filterPath(path: Path): Boolean ={
        if(path.getName().startsWith("1")){
          //过滤不符合的文件
          return true
        }
        false
      }
    })
    val dataStream = fsEnv.readFile(inputFormat,FilePath,FileProcessingMode.PROCESS_CONTINUOUSLY,1000)
    //对数据做转换
    dataStream.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .print()
    fsEnv.execute("FlinkWordCountsFileSource03")
  }
}
