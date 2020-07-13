package com.baizhi

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, IntegerType, StructType}

object CustomUserSingleFunction2 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("window function").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._
    //创建DF
    val df = List(
      (1,"zs",true,1,15000),
      (2,"ls",false,2,18000),
      (3,"ww",true,2,14000),
      (4,"zl",false,1,18000),
      (5,"win7",false,1,16000),
      (6,"wb",false,1,18000),
      (7,"wb2",false,1,20000)).toDF("id","name","sex","dept","salary")
    df.createTempView("t_employee")
    //自定义多行函数
    spark.udf.register("my_sum",new UserDefinedAggregateFunction {
      /**
        * 输入数据的结构类型
        * @return
        */
      override def inputSchema: StructType = new StructType().add("salary",IntegerType)

      /**
        * 缓冲区【用来存放聚合产生的临时结果】的结构类型
        * @return
        */
      override def bufferSchema: StructType = new StructType().add("total",IntegerType)

      /**
        * 聚合操作结束后的返回值类型
        * @return
        */
      override def dataType: DataType = IntegerType

      /**
        * 聚合操作时，输入类型和聚合结果的返回值类型是否匹配
        * @return
        */
      override def deterministic: Boolean = true

      /**
        * 初始化方法
        * @param buffer
        */
      override def initialize(buffer: MutableAggregationBuffer): Unit = {
        //buffer缓冲区的第一个位置 存放了一个初始值
        buffer.update(0,0)
      }

      /**
        * 修改方法
        * @param buffer
        * @param input
        */
      override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
        //获取多行函数的第一个参数值
        val rowValue = input.getInt(0)
        val currentValue = buffer.getInt(0)
        buffer.update(0,rowValue+currentValue)
      }

      /**
        * 合并
        * 将两个buffer中的数据合并 并将最终结果保存到第一个buffer中
        * @param buffer1
        * @param buffer2
        */
      override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
        val b1CurrentValue = buffer1.getInt(0)
        val b2CurrentValue = buffer2.getInt(0)
        buffer1.update(0,b1CurrentValue+b2CurrentValue)
      }

      /**
        * 评估方法 返回聚合结果
        * @param buffer
        * @return
        */
      override def evaluate(buffer: Row): Any = buffer.getInt(0)
    })
    spark.sql("select my_sum(salary) from t_employee").show()
    spark.stop()
  }
}
