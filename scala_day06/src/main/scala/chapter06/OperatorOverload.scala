package chapter06

object OperatorOverload {
  def main(args: Array[String]): Unit = {
    val cat = new cat
    cat + 10
    cat + 20
    cat.+(9)
    println("cat.age="+cat.age)
  }
}
class cat{
  var age = 0
  def +(n:Int):Unit={
    this.age +=n
  }
}