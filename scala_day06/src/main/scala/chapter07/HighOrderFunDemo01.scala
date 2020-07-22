package chapter07

object HighOrderFunDemo01 {
  def main(args: Array[String]): Unit = {
    val res = test(sum2,3.5)
    println("res="+res)
  }

  /**
    * 说明:
    * 1.test就是一个高阶函数
    * 2.f:Double => Double表示一个函数，该函数可以接受一个Double,返回Double
    * 3.n1:Double普通函数
    * 4.f(n1)在test函数中，执行你传入的的函数
    * @param f
    * @param n1
    * @return
    */
  def test(f:Double => Double,n1:Double)={
    f(n1)
  }
  //普通的函数，可以接受一个Double,返回Double
  def sum2(d:Double):Double={
    println("sum2被调用")
    d + d
  }

}
