package com.baizhi

import org.apache.spark.sql.SparkSession

object CustomUserSingleFunction1 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("window function").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._
    //创建DF
    val df = List(
      (1,"zs",true,1,15000),
      (2,"ls",false,2,18000),
      (3,"ww",false,2,14000),
      (4,"zl",false,1,18000),
      (5,"win7",false,1,16000),
      (6,"wb",false,1,18000),
      (7,"wb2",false,1,20000)).toDF("id","name","sex","dept","salary")
    df.createTempView("t_employee")
    //自定义单行函数
    spark.udf.register("sex_converter",(sex:Boolean)=>{
      sex match{
        case true=>"男"
        case false=>"女"
        case _ =>"不男不女"
      }
    })
    spark.sql("select id,upper(name),sex_converter(sex) from t_employee").show()
    spark.stop()
  }
}
