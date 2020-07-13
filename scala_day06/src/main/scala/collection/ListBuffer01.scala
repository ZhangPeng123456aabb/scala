package collection

import scala.collection.mutable.ListBuffer

object ListBuffer01 {
  def main(args: Array[String]): Unit = {
    val a1 = ListBuffer(4,5,6)
    a1.append(7)//追加
    println(a1.mkString(","))
    a1+=8//另外一种追加
    println(a1.mkString(","))
    a1++=List(9,10)//将集合中的元素追加到ListBuffer中
    println(a1.mkString(","))
    3+=:a1//将3置于ListBuffer的头部
    println(a1.mkString(","))
    a1.remove(1)//删除下标为1的元素
    println(a1.mkString(","))
    a1 -=5//删除元素为5
    println(a1.mkString(","))
    println((a1 - 6).mkString(","))
  }
}
