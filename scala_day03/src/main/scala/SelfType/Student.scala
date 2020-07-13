package SelfType

class Student {
  self=> //this起别名
  val x = 2
  def foo = self.x+this.x
}
object Student{
  def main(args: Array[String]): Unit = {
    val s1 = new Student
    println(s1.foo)
  }
}
