package classes

class Student {
  var name:String=""
}
object Student{
  def main(args: Array[String]): Unit = {
    val s1 = new Student
    s1.name="隔壁小刘"
    println(s1.name)
  }
}