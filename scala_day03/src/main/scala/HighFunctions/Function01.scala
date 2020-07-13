package HighFunctions

object Function01 {
  def main(args: Array[String]): Unit = {
    val result = sum _//_ 匹配函数列表
    println(result)
  }
  def sum(x:Int,y:Int)=x+y
}
