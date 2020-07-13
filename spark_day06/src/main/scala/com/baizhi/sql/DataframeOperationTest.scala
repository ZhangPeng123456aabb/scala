package com.baizhi.sql

import org.apache.spark.sql.SparkSession

object DataframeOperationTest {
  def main(args: Array[String]): Unit = {
    val sparkSql = SparkSession.builder().master("local[*]").appName("df operation").getOrCreate()
    import sparkSql.implicits._
    //val df = sparkSql.sparkContext.makeRDD(List((1,"zs",1000.0,true),(2,"ls",2000.0,false),(3,"ww",3000.0,true))).toDF("id","name","salary","sex")
    //打印df结构
    //df.printSchema()
    //df.select($"id",$"name",$"sex").show()
    //df.selectExpr("name as username").show()
    //df.selectExpr("sum(salary)").show()
    //df.selectExpr("id","name as username","salary","salary*12").show()
    //==========================1. withColumn================================
    //添加或者替换【列名相同】字段
    //df.select($"id",$"name",$"salary")
    //添加列
        //.withColumn("year_salary",$"salary"*12)
    //替换已存在的列
//      .withColumn("salary",$"salary"*12)
//        .show()
    //============================2. withColumnRenamed=====================================
//    df.select($"id",$"name",$"salary")
//        //.withColumn("year_salary",$"salary"*12)
//        //替换已存在的列
//      //.withColumn("salary",$"salary"*12)
//      .withColumnRenamed("name","username")
//      .withColumnRenamed("id","userId")
//        .show()
    //=================================3. Drop===============================
//    df.select($"id",$"name",$"salary")
//        .withColumn("salary",$"salary"*12)
//        .withColumnRenamed("name","username")
//        .withColumnRenamed("id","userId")
//        .drop($"username")
//        .show()
    //=============================4.DropDuplicates============================================
    //删除重复数据DropDuplicates 类似于数据库中Distinct[重复数据只保留一个]
//    val df2 = sparkSql.sparkContext.makeRDD(List((1,"zs",1000.0,true),(2,"ls",2000.0,false),(3,"ww",2000.0,false),(4,"zl",2000.0,false))).toDF("id","name","salary","sex")
//    df2.select($"id",$"name",$"salary",$"sex")
//        .withColumn("salary",$"salary"*12)
//        .withColumnRenamed("name","userName")
//        .withColumnRenamed("id","userId")
//        .dropDuplicates("salary","sex")
//        .show()
    //============================5.OrderBy()|Sort()==============================================================
    //排序OrderBy()|Sort()
//    df.select($"id",$"name",$"salary",$"sex")
//        //.orderBy($"salary" desc)
//        //.orderBy($"salary" asc)
//        //.orderBy($"salary" asc,$"id" asc)
//      .sort($"salary" desc)//等价于OrderBy
//        .show()
    //=============================6.GroupBy()==================================================
    //分组groupBy()
//    df
//        .groupBy($"sex")
//        .sum("salary")
//        .show()
    //==============================7.Agg()================================================================
    //agg聚合操作
//    var df3 = List(
//            (1,"zs",true,1,15000),
//            (2,"ls",false,2,18000),
//              (3,"ww",false,1,14000),
//              (4,"zl",false,1,18000),
//              (4,"zl",false,1,16000))
//            .toDF("id","name","sex","dept","salary")
//    import org.apache.spark.sql.functions._
//    df3.groupBy("sex")
//    //.agg(max("salary"),min("salary"),avg("salary"),sum("salary"),count("salary"))
//      //支持单个字段的聚合查询
//      //.agg(Map(("salary","max")))
//    //limit 限制返回结果的条数
//      df3.limit(2)
//      .show()
    //==============================8.Where()==================================================
//    val df4 = List(
//          (1,"zs",true,1,15000),
//          (2,"ls",false,2,18000),
//          (3,"ww",false,2,14000),
//          (4,"zl",false,1,18000),
//          (5,"win7",false,1,16000)).toDF("id","name","sex","dept","salary")
//    df4.select($"id",$"name",$"sex",$"dept",$"salary")
//       //.where("(name like '%s%' and salary>15000) or name = 'win7'")
//      .where(($"name" like "%s%" and $"salary">15000) or $"name"==="win7")
//        .show()
    //==============================9.Pivot===========================================================
    var secoreDF=List(
          (1,"math",85),
          (1,"Chinese",80),
          (1,"english",90),
          (2,"math",90),
          (2,"Chinese",80)).toDF("id","course","score")
//    secoreDF
//        .groupBy($"id")
//      //行转列（重点）
//        .pivot($"course")
//        .max("score")
//        .show()
    //==============================10.na()=============================================
    //对空值的一种处理方式
    //na().fill填充 null赋予默认值
    //na().drop删除null的一行内容
    secoreDF
        .groupBy($"id")
        .pivot($"course")//行转列
        .max("score")
        //.na.fill(Map("english"->59))//为空值赋予一个默认值
        .na.drop()//删除包含空值的一行记录
        .show()
    sparkSql.stop()
  }
}
