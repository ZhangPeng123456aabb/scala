package com.baizhi.sql

import org.apache.spark.sql.SparkSession

object SparkSQLWordCount {
  def main(args: Array[String]): Unit = {
    // 1. sparkSession是spark sql应用入口，内部封装了sparkconf和sparkContext
    val spark = SparkSession
      .builder()
      .appName("the first spark sql example")
      .master("local[*]")
      .getOrCreate()

    // 2. 创建Dataset
    val rdd = spark.sparkContext.makeRDD(List("Hello Hadoop", "Hello Scala")).flatMap(_.split(" ")).map((_, 1))
    // 3. rdd ==> Dataset
    // 4. 导入隐式转换
    import spark.implicits._
    val dataset = rdd.toDS() // (Hello 1) | (Hadoop 1)

    // 5. 对dataset进行sql处理
    dataset
      .where("_1 !='Scala'")
      .groupBy("_1")
      .sum("_2")
      .withColumnRenamed("_1", "word")
      .withColumnRenamed("sum(_2)", "num")
      .show()
    //======================================================================
    val dataFrame = dataset.toDF()
    dataFrame.createOrReplaceTempView("t_word")
    dataFrame.sqlContext.sql("select _1 as word,count(_2) as num from t_word group by _1").show()

    //关闭spark sql应用
    spark.stop()
  }
}
