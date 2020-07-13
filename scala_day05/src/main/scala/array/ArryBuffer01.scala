package array

import scala.collection.mutable.ArrayBuffer

/**
  * 可变数组
  */
object ArryBuffer01 {
  def main(args: Array[String]): Unit = {
    //声明可变数组
    val ab1 = ArrayBuffer[Int]()
    val ab2 = ArrayBuffer[Int](10,20,30)
    //可变数组添加元素
    ab2 += 40
    ab2 += (50)
    ab2.+=(60,70,80)
   // println(ab2)
    //将指定数组中的内容 加入到已有的ab2可变数组
    ab2 ++= ArrayBuffer(90,100)
//    println(ab2)
//    //截取后三个元素 舍弃
//    ab2.trimEnd(3)
//    println(ab2)
    //在指定下标位置 插入指定元素
    ab2.insert(4,45)
    ab2.insert(5,46,47)
    println(ab2)
  }
}
