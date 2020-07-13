package com.baizhi

import org.apache.spark.sql.SparkSession

object DataFrameSqlOpt {
  def main(args: Array[String]): Unit = {
    //1.sparkSession是spark sql应用入口，内部封装了sparkconf和sparkContext
    val spark = SparkSession.builder().master("local[*]").appName("the first spark sql example").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._
    import org.apache.spark.sql._
    //2.创建DataSet
    val df = spark.sparkContext.makeRDD(List("Hello Hadoop","Hello Scala")).flatMap(_.split(" ")).map((_,1)).toDF("word","num")
    //给df起了表明 如果是全局表的话，在访问的时候需要加上数据库名【】
    //对df创建全局临时视图，它产生的表，可以多个spark session共享，他的生命周期和spark application绑定
   // df.createGlobalTempView("t_user")
    //对df创建局部的临时视图，它产生的表，仅供创建spark session 使用，其它的spark session无法获取
    //df.createTempView("t_user")
    //val newSparkSession = spark.newSession()
    //    newSparkSession.sql("select * from global_temp.t_user").show()
    //spark.sql("select * from global_temp.t_user").show()
    //newSparkSession.sql("select * from global_temp.t_user").show()
    //再创建一个session，无法使用局部临时表
    //spark.sql("select * from t_user").show()
    //newSparkSession.sql("select * from t_user").show()
    //============================================like(模糊查询)==========================
    val userDF = List((1,"zs",true,18,15000,1),(2,"ls",false,19,15000,1),(3,"zl",true,19,16000,1)).toDF("id","name","sex","age","salary","dept")
    userDF.createTempView("t_user")
//    spark.sql("select * from t_user where name like '%z%' and age > 18").show()
    //=============================================排序查询========================================
    //排序查询
    //自动将""""引起的内容 进行字符串拼接
//    spark.sql(
//      """
//        |select
//        |*
//        |from
//        |t_user
//        |order by id desc
//      """.stripMargin
//    ).show()
    //================================================分组查询=========================
//    spark.sql(
//      """
//        |select
//        |sex,avg(salary)
//        |as avg_salary
//        |from
//        |t_user
//        |group
//        |by sex
//      """.stripMargin
//    ).show()
    //===================================================限制返回结果的条数=========================
//    spark.sql(
//      """
//        |select
//        |sex,avg(salary)
//        |as avg_salary
//        |from
//        |t_user
//        |group
//        |by sex
//        |limit 1
//      """.stripMargin
//    ).show()
    //======================================================having分组后的过滤======================================
//    spark.sql(
//      """
//        |select
//        |sex,avg(salary)
//        |as avg_salary
//        |from
//        |t_user
//        |group
//        |by sex
//        |having
//        |sex=false
//      """.stripMargin
//    ).show()
    //=========================================================Case....when语句=====================================================
//    spark.sql(
//      """
//        |select
//        |id,name,salary,age,
//        |case sex
//        |when true
//        |then '男'
//        |when false
//        |then '女'
//        |else
//        |'中性'
//        |end
//        |as newSex
//        |from
//        | t_user
//      """.stripMargin
//    ).show()
    //===================================Pivot(行转列)=================================
    //pivot
//    val scoreDF = List(
//      (1,"语文",100),
//      (1,"数学",100),
//      (1,"英语",100),
//      (2,"数学",79),
//      (2,"语文",80),
//      (2,"英语",100),
//      (2,"英语",120)
//    ).toDF("id","course","score")
//    scoreDF.createOrReplaceTempView("t_course")
//    //注意：缺省的列会作为分组的依据
//    spark.sql(
//      """
//        |select
//        |*
//        |from
//        |t_course
//        |pivot(max(score) for course in('语文','数学','英语'))
//      """.stripMargin
//    ).show()
    //======================================Cube(多维度分组)==================================
//    val df2=List(
//      (110,50,80,80),
//      (120,60,95,75),
//      (120,50,96,70)).toDF("height","weight","uiq","ueq")
//    df2.createTempView("tt_user")
//    spark.sql(
//      """
//        |select
//        |height,uiq,avg(uiq)
//        |from
//        |tt_user
//        |group by
//        |cube(height,uiq)
//      """.stripMargin
//    ).show()
      //=====================================Join表连接查询==============================
//    val userInfoDF = spark.sparkContext.makeRDD(List((1,"zs"),(2,"ls"),(3,"ww"))).toDF("id","name")
//    val orderInfoDF = spark.sparkContext.makeRDD(List((1,"iphone",1000,1),(2,"mi9",999,1),(3,"连衣裙",99,2))).toDF("oid","product","price","uid")
//    userInfoDF.createTempView("ttt_user")
//    orderInfoDF.createTempView("t_order")
//    spark.sql(
//      """
//        |select
//        |*
//        |from
//        |ttt_user t1
//        |inner join
//        |t_order t2
//        |on
//        |t1.id=t2.uid
//      """.stripMargin
//    ).show()
    //=====================================子查询(类似于SQL的子查询)================================
    val df5=List(
      (1,"zs",true,1,15000),
      (2,"ls",false,2,18000),
      (3,"ww",false,2,14000),
      (4,"zl",false,1,18000),
      (5,"win7",false,1,16000),
      (6,"win8",false,1,16000)).toDF("id","name","sex","dept","salary")
    df5.createTempView("t_employee")
//    spark.sql(
//      """
//        |select
//        |id,
//        |name,
//        |sex,
//        |dept
//        |from (select * from t_employee)
//      """.stripMargin
//    ).show()
    //======================================窗口函数========================================
    //窗口函数名()over([partition by 分区字段] order by 字段 asc | desc [range | rows between unbounded preceding and unbounded following])
    spark.sql(
      """
        |select
        |id,name,sex,dept,salary,
        |sum(id) over(partition by dept order by salary rows between unbounded preceding and unbounded following)as sum_id,
        |sum(id) over (partition by dept order by salary) as sum_id2,
        |sum(id) over() as sum_id3,
        |sum(id) over(partition by dept order by salary rows between 1 preceding and 1 following) as sum_id4,
        |sum(id) over(partition by dept order by salary range between 1000 preceding and 2000 following) as sum_id5,
        |row_number() over(partition by dept order by salary)as rn,
        |rank(salary) over(partition by dept order by salary)as salary_rank,
        |dense_rank(salary) over(partition by dept order by salary asc)as salary_rank2,
        |lag(salary,2) over(partition by dept order by salary desc)as lag2
        |from
        |t_employee
      """.stripMargin
    ).show()
    spark.stop()
  }
}
//第一个表示 以任意的一行数据为基准都可以看到窗口的的所有的数据
//第二个表示 没有加任何数据的可视范围，使用默认的数据可视范围 rowsBetween[Long.min_value,0]
//第三个表示 over没有声明任何的窗口函数内容，则在每行显示整张表的聚合结果//agg=15
//第四个表示 以当前行为基准 上一行和下一行rowsBetween[-1,1]
//第五个表示 数据可视范围区间 range between[当前数据排序字段为基准-下界，当前数据排序字段为基准+上界]
//第六个表示 排序窗口函数 row_number()给窗口的数据添加一个序号 类似于Oracle伪列rownum
//第七个表示 对窗口函数 rank(排名字段)给窗口的数据按照名字段信息排名 注意：非密集或者非连续的排名
//第八个表示 对窗口函数 dense_rank(连续密集排名字段)给窗口的数据按照名字段信息排名 注意：密集或者连续的排名
//第九个表示 获取往上两行的salary的值 作为当前行窗口得值