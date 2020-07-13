package practise

import scala.collection.mutable.ArrayBuffer

object ArrayBuffer {
  def main(args: Array[String]): Unit = {
    val a1 = new ArrayBuffer[Byte]()
//    // 注意： += 语法是给ArrayBuffer添加元素
//    a1 += 1
//    a1 += 2
//    // 一次性添加多个元素
//    a1 += (3,4,5)
//    // 注意： ++= 将数组中的所有元素添加到ArrayBuffer中
//    a1 ++= Array[Byte](6,7,8)
//    // 从末尾截断指定个数的元素
//    a1.trimEnd(3)
//    // 从下标5 添加元素 6，7，8
//    a1.insert(5,6,7,8)
//    // 跳跃遍历
//    for ( i <- 0 until(a1.length,2) ) {
//      print(a1(i) +"\t")
  //  }
    val a2 = a1.toArray
    //a2(2)=0
    val a3 = a2.toBuffer
    println(a2)
    println(a3)
  }
}
