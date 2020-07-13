package com.baizhi.keyedstate
import org.apache.flink.api.common.functions.{AggregateFunction, RichMapFunction}
import org.apache.flink.api.common.state.{AggregatingState, AggregatingStateDescriptor}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object FlinkWordCountsAggregatingState {
  def main(args: Array[String]): Unit = {
    //1.创建StreamExecutionEnvironment
    val fsEnv = StreamExecutionEnvironment.getExecutionEnvironment
    fsEnv.disableOperatorChaining()//
    //2.创建DataStream -细化
    val dataStream: DataStream[String] = fsEnv.socketTextStream("Spark",5555)
    //3.对数据做转换 1 zhangsan 销售部 10000
    dataStream.map(_.split("\\s+"))
      .map(ts=>Employee(ts(0),ts(1),ts(2),ts(3).toDouble))
      .keyBy("dept")
      .map(new RichMapFunction[Employee,(String,Double)] {
        var aggregatingState:AggregatingState[Double,Double]= _

        override def open(parameters: Configuration): Unit = {
          val asd=new AggregatingStateDescriptor[Double,(Double,Int),Double]("agggstate",
            new AggregateFunction[Double,(Double,Int),Double] {
              override def createAccumulator(): (Double, Int) = (0.0,0)

              override def add(value: Double, accumulator: (Double, Int)): (Double, Int) = {
                var total=accumulator._1
                var count=accumulator._2
                (total+value,count+1)
              }
              override def merge(a: (Double, Int), b: (Double, Int)): (Double, Int) = {
                (a._1+b._1,a._2+b._2)
              }
              override def getResult(accumulator: (Double, Int)): Double = {
                accumulator._1/accumulator._2
              }

            }
            ,createTypeInformation[(Double,Int)])

          aggregatingState=getRuntimeContext.getAggregatingState(asd)
        }

        override def map(value: Employee): (String, Double) = {
          aggregatingState.add(value.Salary)
          (value.dept,aggregatingState.get())
        }
      })
      .print()

    fsEnv.execute("FlinkWordCountsQuickStart")
  }
}
case class Employee(id:String,name:String,dept:String,Salary:Double)