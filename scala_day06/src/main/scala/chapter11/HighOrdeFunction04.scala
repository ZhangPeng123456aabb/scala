package chapter11

object HighOrdeFunction04 {
  def main(args: Array[String]): Unit = {
    def test(x:Double) = {
      (y:Double) => x * x *y
    }
    val res = test(2.0)(3.0)
    println("res = "+res)
  }
}
