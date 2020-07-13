package array

import scala.collection.mutable.ArrayBuffer
import scala.util.Sorting

object ArrayBuffer02 {
  def main(args:Array[String]):Unit={
    val a1 = new ArrayBuffer[Byte]()
    a1+=1
    a1+=2
    a1+=(3,4,5)
    a1++=Array[Byte](6,7,8)
//    for(a<- a1){
//      println(a)
//    }
//    for(i<- 0 until(a1.length,2)){
//      println(a1(i)+"\t")
//    }
    println(a1.sum)//求和36
    println(a1.max)//求最大值
    println(a1.min)//求最小值
    println(a1.mkString)//把数组转化为字符串
    println(a1.mkString(","))//设置分隔符
    println(a1.sorted.mkString)//排序
    println(a1.mkString("<",",",">"))//<1,2,3,4,5>
    println(a1.mkString)
  }
}
