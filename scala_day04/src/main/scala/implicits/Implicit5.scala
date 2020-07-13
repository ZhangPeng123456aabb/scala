package implicits

//import CC._
object Implicit5 {
  def main(args: Array[String]): Unit = {
    //sayHi("lisi")
    implicit  def str2Student(str:String):Student2={
      new Student2(str)
    }
    val s1 = str2Student("lisi")
    println(s1)
  }
  def sayHi(student:Student2)=println(student.name)

  implicit def  str2Student(str:String):Student2={
    new Student2(str)
  }
}
class Student2(var name:String)
//object CC{
//  implicit def str2Student(Str:String):implicits.Student2={
//    new implicits.Student2(Str)
//  }
//}
