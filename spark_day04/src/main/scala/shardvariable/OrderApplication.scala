package shardvariable

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 每一个用户的花费总额
  */
object OrderApplication {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("shard variable")
    val sc = new SparkContext(conf)
    //用户信息的变量
    val userInfo = Map("001"->"zs","002"->"ls","003"->"ww")
    //订单的RDD
    val orderItems=List(
      ("001",OrderItem("iphone11",4999,2)),
      ("002",OrderItem("Dior",129,1)),
      ("003",OrderItem("刮胡刀",15,1)),
      ("001",OrderItem("收音机",85,1))
    )
    val rdd = sc.makeRDD(orderItems,2)
//    //不使用广播变量，算子函数中使用到userinfo的外部变量，每一个任务都会拷贝一个变量副本
//    rdd.map(t2=>(t2._1,t2._2.price*t2._2.num))
//      .reduceByKey(_+_)
//      .foreach(t2=>println(t2._1+" "+userInfo(t2._1)+" "+t2._2))
    val broadcastVar = sc.broadcast(userInfo)
    rdd.map(t2=>(t2._1,t2._2.price*t2._2.num))
      .reduceByKey(_+_)
      .foreach(t2=>{
        //广播变量
        println(t2._1+"\t"+broadcastVar.value(t2._1)+"\t"+t2._2)
      })

  }
}
case class OrderItem(var productName:String,var price:Double,var num:Int)