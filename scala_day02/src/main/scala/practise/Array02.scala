package practise

import scala.util.Sorting

object Array02 {
  def main(args: Array[String]): Unit = {
    val a1 = Array(3,2,1,4,5)
    println(a1.min)
    println(a1.max)
    println(a1.sum)
    println(a1.mkString)
    println(a1.mkString(","))
    println(a1.mkString("<",",",">"))
    Sorting.quickSort(a1)
    a1.foreach(println)
  }
}
