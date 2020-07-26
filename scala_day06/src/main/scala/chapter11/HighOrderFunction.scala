package chapter11

object HighOrderFunction {
  def main(args: Array[String]): Unit = {
    def test(f:Double => Double,n1: Double)={
      f(n1)
    }
    //sum是接收一个Double,返回一个Double
    def sum(d: Double): Double = {
      d + d
    }
    val res = test(sum,6.0)
    println("res= "+res)
  }
}
