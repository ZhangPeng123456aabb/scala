package com.baizhi

import org.apache.spark.sql.SparkSession

object DataframeOperationTest {
  def main(args: Array[String]): Unit = {
    val sparkSql = SparkSession.builder().master("local[*]").appName("df operation").getOrCreate()
    import sparkSql.implicits._
    var df = sparkSql.sparkContext.makeRDD(List((1,"zs",1000.0,true),(2,"ls",2000.0,false),(3,"ww",3000.0,false))).toDF("id","name","salary","sex")
    //打印df的结构
    //df.printSchema()
    //默认输出df中前20行数据
    //查询指定的字段
    // df.select("id","name","sex").show()
    //$是另外一种写法[隐式转换]字符串名==>Column对象
    //df.select($"id",$"name",$"salary").orderBy($"salary" desc).show()
    //df.selectExpr("id","name","sex","salary*12").show()
    //=============================================
    //df.select("id","name","sex","salary")
     //   .withColumn("salary",$"salary"*12).show()
    //=============================================
    //df.select("id","name","sex","salary").withColumn("year_salary",$"salary"*12).show()
    //=======================================================
//    df.select("id","name","sex","salary")
//        .withColumn("year_salary",$"salary"*12)
//        .withColumnRenamed("id","userId")
//        .withColumnRenamed("name","userName")
//        .show()
    //==========================================================
//    df.select("id","name","salary","sex")
//        .withColumn("salary",$"salary"*12)
//        .withColumnRenamed("name","userName")
//        .withColumnRenamed("id","userId")
//        .drop("userName")
//        .show()
    //===============================================================
    val df2 = sparkSql.sparkContext.makeRDD(List((1,"zs",1000.0,true),(2,"ls",2000.0,false),(3,"ww",2000.0,false),(4,"zl",2000.0,false))).toDF("id","name","salary","sex")
//    df2.select("id","name","salary","sex")
//        .withColumn("year_salary",$"salary"*12)
//        .withColumnRenamed("id","userId")
//        .withColumnRenamed("name","userName")
//      //只有salary和sex相同时，才会删除相同的行数据
//        .dropDuplicates("salary","sex")
//        .show()
    //================================================================
   // df.select("id","name","salary","sex")
        //.orderBy($"salary" desc)
        //.orderBy($"salary" asc)
        //以第一个排序规则为准
//        .orderBy($"salary" asc,$"id" desc)
//        .show()
    //===================================================================
//    df.groupBy($"sex")
//      .sum("salary")
//      .withColumnRenamed("sum(salary)","Total")
//      .show()
    //======================================================================
    //List聚合操作
//    var df3 = List(
//    (1,"zs",true,1,15000),
//    (2,"ls",false,2,18000),
//    (3,"ww",true,2,14000),
//    (4,"zl",false,1,18000),
//    (4,"zl",true,1,16000))
//    .toDF("id","name","sex","dept","salary")
//    import org.apache.spark.sql.functions._
//    df3.groupBy("sex")
//        //.agg(max("salary"),min("salary"),avg("salary"),sum("salary"),count("salary"))
//        .agg(Map(("salary","max")))
//        .show()
    //========================================================
    //df.limit(2).show()
    //====================================================================
//    val df4 = List(
//  (1,"zs",true,1,15000),
//  (2,"ls",false,2,18000),
//  (3,"ww",false,2,14000),
//  (4,"zl",false,1,18000),
//    (5,"win7",false,1,16000))
//    .toDF("id","name","sex","dept","salary")
//    df4.select("id","name","sex","dept","salary")
//        .where(($"name" like "%s%" and $"salary">15000) or $"name"==="win7").show()
    //=================================================================
//    var scoreDF = List(
//  (1,"math",85),
//  (1,"Chinese",80),
//  (1,"english",90),
//  (2,"math",90),
//  (2,"Chinese",80)).toDF("id","course","score")
//    scoreDF
//        .groupBy($"id")
//        .pivot($"course")//行转列重点
//        .max("score")
//      //删除包含空值的一行
//        .na.drop()
//        .show()
    sparkSql.stop()
  }
}
