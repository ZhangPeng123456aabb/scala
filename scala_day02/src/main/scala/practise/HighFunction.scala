package practise

object HighFunction {
  def main(args: Array[String]): Unit = {
    val a = Array(1,2,3,4,5,6)
    a.map(_*2).filter(_ != 8).foreach(println)
  }
}
