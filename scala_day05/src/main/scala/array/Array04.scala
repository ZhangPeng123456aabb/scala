package array

/**
  * 多维数组
  */
object Array04 {
  def main(args: Array[String]): Unit = {
    val a1 = Array.ofDim[Int](3,4)
    //println(a1.mkString(","))
    println(a1(0)(2))
    a1(0)(2)=100
    println(a1(0)(2))
  }
}
