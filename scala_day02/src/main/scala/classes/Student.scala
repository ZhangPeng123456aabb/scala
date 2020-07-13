package classes

object Student {
  def main(args: Array[String]): Unit = {
    val s1 = new Student
    println(s1.name)
    s1.name="隔壁小刘"
  }
}
class Student{
  var name : String = ""
}