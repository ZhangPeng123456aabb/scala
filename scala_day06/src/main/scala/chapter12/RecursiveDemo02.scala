package chapter12

import java.text.SimpleDateFormat
import java.util.Date

object RecursiveDemo02 {
  def main(args: Array[String]): Unit = {
    //1.执行前的时间
    val now: Date = new Date()
    val dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = dateFormat.format(now)
    println("执行前的时间 date= "+date)//输出时间

    //2.测试耗时
    var num = BigInt(1)//num
    var sum = BigInt(0)//和
    var res = mx(num,sum)
    println("res= "+res)//耗时7秒

    //3.执行后的时间
    val now2: Date = new Date()
    val date2 = dateFormat.format(now2)
    println("执行后的时间 date= "+date2)//输出时间

  }
  //递归的方式来统计 1+.....+num 和
  def mx(num:BigInt,sum:BigInt):BigInt={
    if(num <= 99999999l) mx(num+1,sum+num)
    else return sum
  }
}
