package chapter07

object Exercise01 {
  def main(args: Array[String]): Unit = {
    val names = List("Alice","Bob","Nick")
    val name1 = names.map(upper)
    println("name1= "+name1)
  }
  def upper(s:String):String={
    s.toUpperCase
  }
}
