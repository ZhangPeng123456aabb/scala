                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     package com.baizhi.sql.dataFrame

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{BooleanType, IntegerType, StringType, StructType}

object df {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("DataFrame Test").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._
    //1.通过JSON创建
//    val df = spark.read.json("file:///F:\\study\\IDEA_project\\Scala\\spark_day06\\src\\main\\resources")
//    df结构
//    df.printSchema()
//    df.show()
    //2.通过样例类方式进行创建
//    List(User2(1,"zs",true),User2(2,"ls",false)).toDF.show()
    //3.通过Tuple方式进行创建
//    List((1,"zs",true),(2,"ls",false)).toDF("id","name","sex").show()
    //4.通过RDD进行创建
//    val dataFrame = spark.sparkContext.makeRDD(List((1,"zs",true),(2,"ls",false))).toDF("id","name","sex")
//    dataFrame.printSchema()
//    dataFrame.show()
    //5.通过样例类转换进行创建
//    spark.sparkContext.parallelize(List(User2(1,"zs",true),User2(2,"ls",false))).toDF("id","name","sex").show()
    //6.通过RDD【Row】创建DF
//    val rdd = spark.sparkContext.parallelize(List((1,"zs",true),(2,"ls",false))).map(t=>Row(t._1,t._2,t._3))
//    val schema = new StructType()
//        .add("id",IntegerType)
//        .add("name",StringType)
//        .add("sex",BooleanType)
//    val df = spark.createDataFrame(rdd,schema)
//    df.show()
    //7.通过JAVA Bean创建DF
//    val personList = List(new Person(1,"zs"),new Person(2,"ls"))
//    val rdd = spark.sparkContext.makeRDD(personList)
//    val df = spark.createDataFrame(rdd,classOf[Person])
//    df.show()
    //8.通过RDD[case class]创建DF

//    val userRDD = spark.sparkContext.makeRDD(List(ScalaUser(1,"zs",true)))
//    var frame = spark.createDataFrame(userRDD)
//    frame.show()
    //通过RDD Tuple方式创建DF
    val rdd:RDD[(Int,String,Boolean)] = spark.sparkContext.makeRDD(List((1,"zs",true),(2,"ls",false)))
    val dataFrame = spark.createDataFrame(rdd)
    dataFrame.show()
    spark.stop()
  }
}
case class User2(id:Int,name:String,sex:Boolean)
case class ScalaUser(id:Int,name:String,sex:Boolean)