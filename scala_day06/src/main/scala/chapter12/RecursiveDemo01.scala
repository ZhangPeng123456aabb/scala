package chapter12

import java.text.SimpleDateFormat
import java.util.Date


object RecursiveDemo01 {
  def main(args: Array[String]): Unit = {
    //计算1--50的和，使用常规的while来完成
    val now: Date = new Date()
    val dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = dateFormat.format(now)
    println("执行前的时间 date= "+date)//输出时间

    var res = BigInt(0)//存放计算的结果
    var num = BigInt(1)//num变化的数
    var maxVal = BigInt(99999999l)//BigInt(99999999l)[测试效率大数]
    //使用传统的while
    while(num <= maxVal){
      res += num //累计
      num += 1
    }
    println("res= "+res)//耗时6秒

    val now2: Date = new Date()
    val date2 = dateFormat.format(now2)
    println("执行后的时间 date= "+date2)//输出时间
  }
}
