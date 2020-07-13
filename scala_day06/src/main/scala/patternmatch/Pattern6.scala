package patternmatch

object Pattern6 {
  def main(args: Array[String]): Unit = {
    //sayHello(Student2("zs",18,"A123"))//
    //sayHello(Teacher2("zs",20,"A123"))
    sayHello(NoBody2("zs"))
  }
  def sayHello(p:Person2):Unit={
    p match{
      //样例类的模式匹配 自动调用  伴生类对象中的unhappy方法 将内容提取出来赋予变量
      case Student2(name,age,stuNo)=>println(s"学生：$name 年龄:$age  学号:$stuNo")
      case Teacher2(name,age,teaNo)=>println(s"老师:$name  年龄:$age  学号:$teaNo")
      case NoBody2(name)=>println(s"其他人:$name")
      case  _o=>println(_o)
    }
  }
}
//样例类 案例类
abstract class Person2
case class Student2(name:String,age:Int,stuNo:String)extends Person2
case class Teacher2(name:String,age:Int,teaNo:String)extends Person2
case class NoBody2(name:String)extends Person2
