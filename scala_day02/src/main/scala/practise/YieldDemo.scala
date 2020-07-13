package practise

object YieldDemo {
  def main(args: Array[String]): Unit = {
    val result = for(a<- 1 to 5) yield a*2
    println(result)
  }
}
