package com.baizhi.windows
import java.text.SimpleDateFormat
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

object FlinkWordCountsTumblingTimeWindowsProcessWindowFunction02 {
  def main(args: Array[String]): Unit = {
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    //2.创建DataStream -细化
    val dataStream: DataStream[String] = fsEnv.socketTextStream("Spark",9999)

    //3.对数据做转换
    dataStream.flatMap(_.split("\\s+"))
      .map((_,1))
      .keyBy(_._1)
      .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
      .reduce((v1:(String,Int),v2:(String,Int))=>(v1._1,v1._2+v2._2),new ProcessWindowFunction[(String,Int),(String,Int),String,TimeWindow] {

        override def process(key: String,
                             context: Context,
                             elements: Iterable[(String, Int)],
                             out: Collector[(String, Int)]): Unit = {

          val w = context.window
          val sdf = new SimpleDateFormat("HH:mm:ss")

          println(sdf.format(w.getStart)+" ~ "+ sdf.format(w.getEnd))

          val total = elements.map(_._2).sum
          out.collect((key,total))
        }
      })
      .print()

    fsEnv.execute("FlinkWordCountsQuickStart")
  }
}
