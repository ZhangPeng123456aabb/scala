package collection

import scala.collection.mutable.ListBuffer
import scala.collection.mutable._
import scala.collection.JavaConverters._
/**
  * scala<---->java
  */
object List1{
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5)
    //println(list(0))
    val list2=Nil
    //println(list2)
    val li = List(4,5,6)
    val list4 = li:+7
    //println(list4.mkString(","))
    val list5=3+:list4
    //println(list5.mkString(","))
    val list6 = List(1,2,3)
    //将List("a","b")作为一个整体添加到list6头部
    val list7 = List("a","b")::list6
    //println(list7.mkString(","))
    val list8 = List("a","b"):::list6
    //List("a","b")内容展开，添加到list6头部
    //println(list8.mkString(","))
   // println(list8==list6)
    println("==============类型转换=================")
    val k:java.util.List[Int] = ListBuffer(1,2,3).asJava//隐式转换
    k.add(4)
    for(x <- 0 until (k.size())){
      println(k.get(x))
    }
    val dd = k.asScala//将java.util.List转换为scala的集合
    println(dd.mkString(","))
  }
}
