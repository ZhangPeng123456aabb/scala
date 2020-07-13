package HighFunctions

object Function05 {
  def main(args: Array[String]): Unit = {
    var num = 1 //外部有变量
    val result = (x:Int)=>num+x//匿名函数引用外部变量，闭包后 多次调用 共享相同的外部变量
    println(result(1))//2
    num+=10
    println(result(1))//12
  }
}
