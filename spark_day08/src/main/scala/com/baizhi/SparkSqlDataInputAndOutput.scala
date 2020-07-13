package com.baizhi

import org.apache.spark.sql.{SaveMode, SparkSession}

object SparkSqlDataInputAndOutput {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("Spark SQl Input And Output").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    val df = spark.read.json("file:///F:\\study\\IDEA_project\\Scala\\spark_day08\\src\\main\\resources")
    val df1 = spark.read.json("file:///H:\\大数据视频\\京东20万条评论数据\\京东20万条评论数据")
    df.createTempView("t_user")
    df1.createTempView("t_product")
    //===============================JSON==============================
//    spark.sql("select id,name from t_user")
//      .write.format("json").save("file:///D://result")
    //=================================Paquet==========================
    //基于列式存储的文件格式，底层会将数据编码成二进制数据
    //读parquet文件内容 创建df
   // val df1 = spark.read.parquet("file:///D://result")
    //写出parquet文件格式
//    spark.sql("select id,name from t_user")
//        .write.save("file:///D:\\result2")
    //=================================ORC===========================
    //矢量化文件格式，比较节省内存空间
    //spark.sql("select * from t_user").write.orc("file:///d:\\result3")
    //读orc文件内容 创建df
    //val df1 = spark.read.orc("file:///D:\\result3").show()
    //==================================CSV==========================
    //写出csv文件格式
//    df.write
//        .format("csv")
//        .option("sep",",")
//        .option("inferSchema","true")
//        .option("header","true")
//        .save("file:///D://result1")
    //读csv文件内容 创建df
//    val df1 = spark.read
//    .option("sep",",")
//    .option("interSchema","true")
//    .option("header","true")
//    .csv("file:///d:\\result1").show()
    //================================JDBC====================
    //写出JDBC MySql数据库中
    spark
        .sql("select * from t_product")
        .write
        .format("jdbc")
        .mode(SaveMode.Overwrite)//覆盖
        .option("user","root")
        .option("password","123456")
        .option("url","jdbc:mysql://localhost:3306/ajax")
        .option("dbtable","t_product")
        .save()
    //读取jdbc mysql中的数据，创建df
//    val df2 =spark
//    .read
//    .format("jdbc")
//    .option("user","root")
//    .option("password","123456")
//    .option("url","jdbc:mysql://localhost:3306/ajax")
//    .option("dbtable","t_product").load()
//
//    df2.rdd.foreach(row=>{
//      //df转rdd
//      println(row.getLong(0)+"\t"+row.getString(1))
//      //row中获取元素的方法 内容为下标
//    })
    spark.stop()
  }
}
