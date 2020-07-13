package array

object Array04 {
  def main(args: Array[String]): Unit = {
    //声明多维数组
    val a = Array.ofDim[Int](10,5)//二维数组
    println(a(0)(2))
    a(0)(2)=10
    println(a(0)(2))
  }
}
