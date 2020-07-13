package com.baizhi
import org.apache.spark.{SparkConf, SparkContext}
object ActionTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("transformation test").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd = sc.makeRDD(1 to 10, 2)
    //val result = rdd.reduce((x,y)=>(x+y))
    //println(result)
    //=============================================
    //    val first=rdd.first()
    //    println(first)
    //=============================================
    //    val newArray = rdd.take(4)
    //    println(newArray.mkString(","))
    //==============================================
    //获取指定数量的样例数据
    //    val newArray2=rdd.takeSample(false,3)
    //    println(newArray2.mkString(","))
    //先排序后拿值
    //    val newArray3 = rdd.takeOrdered(4)(new Ordering[Int] {
    //      override def compare(x: Int, y: Int): Int = if (x > y) -1 else 1
    //    })
    //    println(newArray3.mkString(","))
    rdd.saveAsTextFile("file:///D://result")
    //rdd.map((_, 1)).saveAsSequenceFile("file:///d://result4")
//val rdd2 = sc.parallelize(List(("A","a"),("B","b"),("C","c"),("A","a")))
//    val map = rdd2.countByKey()
//    map.foreach(t=>println(t._1+"\t"+t._2))
  }
}



