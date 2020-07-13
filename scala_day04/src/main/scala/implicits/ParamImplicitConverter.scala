package implicits

object ParamImplicitConverter {
  def main(args: Array[String]): Unit = {
    val s1 = new Student("zs")
    sayHi(s1)
    sayHi("ls")
  }
  implicit def str2Student(str:String):Student={
    new Student(str)
  }
  def sayHi(student:Student)=println(s"$student")
}
class Student(val name:String){

  override def toString = s"implicits.Student($name)"
}