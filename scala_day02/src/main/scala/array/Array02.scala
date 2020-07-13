package array

import scala.util.Sorting

object Array02 {
  def main(args: Array[String]): Unit = {
    val a1 = Array(5,4,3,2,1)
    println(a1.sum)
    println(a1.max)
    println(a1.min)

    println(a1.mkString)
    println(a1.sorted.mkString)
    println(Sorting.quickSort(a1))
    println(a1.mkString(","))
    println(a1.mkString("[","|","]"))
  }
}
