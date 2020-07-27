package chapter12

object RecursiveFactoria {
  def main(args: Array[String]): Unit = {
    println(Factorial(10))//求 1 * 2 * ... * 10的阶乘
  }
  def Factorial(n:Int):Int=
    if (n==0) 1 else n * Factorial(n-1)
}
