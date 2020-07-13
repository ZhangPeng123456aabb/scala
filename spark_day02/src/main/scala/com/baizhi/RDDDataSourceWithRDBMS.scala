package com.baizhi

import java.sql.{DriverManager, PreparedStatement, ResultSet}

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.mapred.lib.db.{DBConfiguration, DBInputFormat, DBWritable}
import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

object RDDDataSourceWithRDBMS {
  def main(args: Array[String]): Unit = {
    //val conf = new SparkConf().setMaster("local[*]").setAppName("rdd with rdbms")
    val conf = new SparkConf().setMaster("local[*]").setAppName("rdd with rdbms")
    val sc = new SparkContext(conf)
    //通过RDBMS创建Spark RDD对象
    //方式一
    //method1(sc)
    //方式二

    method1(sc)
    //method2(sc)
    sc.stop()
  }
  def method1(sc:SparkContext)={
    val rdd = new JdbcRDD[(Int, String)](
      sc,
      () => {
        Class.forName("com.mysql.jdbc.Driver")
        DriverManager.getConnection("jdbc:mysql://localhost:3306/ajax", "root", "123456")
      },
      "select * from order",
      1, //第一个?值
      6,
      1, //分区数
      rs => (rs.getInt("ProductID"), rs.getString("City")) //元组 保存查询的结果
    )
    rdd.foreach(println)

  }

  /**
    * 传统的方式 DBInputFormat
    */
  def method2(sc:SparkContext): Unit ={
    val conf = new Configuration()
    //数据源的连接参数
    conf.set(DBConfiguration.DRIVER_CLASS_PROPERTY,"com.mysql.jdbc.Driver")
    conf.set(DBConfiguration.URL_PROPERTY,"jdbc:mysql://hadoopnode00:3306/spark")
    conf.set(DBConfiguration.USERNAME_PROPERTY,"root")
    conf.set(DBConfiguration.PASSWORD_PROPERTY,"123456")
    //配置查询SQL语句
    conf.set(DBConfiguration.INPUT_QUERY,"select * from user")
    conf.set(DBConfiguration.INPUT_COUNT_QUERY,"select count(*) from user")
    //必须配置实体类的全限定名
    conf.set(DBConfiguration.INPUT_CLASS_PROPERTY,"com.baizhi.UserWritable")
    val rdd = sc.newAPIHadoopRDD(conf,classOf[DBInputFormat[UserWritable]],classOf[LongWritable],classOf[UserWritable])
    rdd.foreach(t=>println(t._2.ProductID+"\t"+t._2.City))
  }
}
class UserWritable extends DBWritable{
  var ProductID:Int=_
  var City:String=_
  var Amount:Int=_
  var Money:Double=_
  var UserID:Int=_
  override def write(preparedStatement: PreparedStatement): Unit = {
    preparedStatement.setInt(1,this.ProductID)
    preparedStatement.setString(2,this.City)
    preparedStatement.setInt(3,this.Amount)
    preparedStatement.setDouble(4,this.Money)
    preparedStatement.setInt(5,this.UserID)
  }

  override def readFields(resultSet: ResultSet): Unit = {
    this.ProductID=resultSet.getInt("ProductID")
    this.City=resultSet.getString("City")
    this.Amount=resultSet.getInt("Amount")
    this.Money=resultSet.getDouble("Money")
    this.UserID=resultSet.getInt("UserID")
  }
}