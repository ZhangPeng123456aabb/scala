package classes

class Student {
  private var name:String=""
//  private[this] val sex : Boolean = false
  private[this] val wc : Boolean = false
  def sayHello():Unit={
    s"name:$name,wc:$wc,sex:"+Student.sex
  }
}
object Student{
  private var sex:Boolean=false
  def main(args: Array[String]): Unit = {
    val student = new Student
    student.name="张三"
    println(student.name)
    //student.sex  error
    //student.wc    error
  }
}
