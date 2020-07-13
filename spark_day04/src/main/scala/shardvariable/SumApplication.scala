package shardvariable

import org.apache.spark.{SparkConf, SparkContext}

object SumApplication {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("shard variable")
    val sc = new SparkContext(conf)
    val rdd = sc.makeRDD(Array(1,2,3,4,5,6),3)
    //声明一个名为Counters累加器 0
    val counters = sc.longAccumulator("Counters")
    rdd.foreach(n=>{
      //通过了累加器，进行计算结果的累加
      counters.add(n)
    })
    //把计算结果返回给Driver端，并进行打印
    println("数组的累加之和为"+counters.value)
    sc.stop()
  }
}
