package chapter11

object AnonymouseFunction01 {
  def main(args: Array[String]): Unit = {
    /**
      * 请编写一个匿名函数，可以返回2个整数的和，并输出该匿名函数的类型
      */
    val f1 = (n1:Int,n2:Int) =>{
      println("匿名函数被调用")
      n1+n2
    }
    println("f1类型="+f1)
    println(f1(10,30))
  }
}
