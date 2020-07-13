package hello

object HelloWorld {
  def main(args: Array[String]): Unit = {
    var a = 10
    println("Hello World")
    println("Hello World")
    println()
    println(m1())
    //m2()
  }
  def m1(){}
  def m2():Nothing={
    throw new RuntimeException
  }
}

