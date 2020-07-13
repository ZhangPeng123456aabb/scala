package com.baizhi.window
import org.apache.spark.sql.{DataFrame, Dataset, SaveMode, SparkSession}

object SparkSQLTestApp {
  def main(args: Array[String]): Unit = {
    //1.sparkSession是spark sql应用入口，内部封装了sparkconf和sparkContext
    val spark = SparkSession.builder().master("local[*]").appName("the first spark sql example").getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._
    import org.apache.spark.sql.functions._
    val df = spark
      .read
      .format("jdbc")
      .option("user", "root")
      .option("password", "123456")
      .option("url", "jdbc:mysql://localhost:3306/ajax")
      .option("dbtable", "t_order")
      .option("driver","com.mysql.jdbc.Driver")
      .load()
    df.createTempView("order")
    spark.sql(
      """
        |select
        |o.City,
        |max(Amount),
        |max(Amount*Money) AmountMoney
        |from
        |order o
        |group by city
        |order by AmountMoney
      """.stripMargin
    ).show()

    spark.stop()
  }
}