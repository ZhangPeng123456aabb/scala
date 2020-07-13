package classes


class Student1(name:String,age:Int){
//  var name:String=""//必须初始化
//  var age:Int=0
  def sayHello():String={
    "Hello:"+name+",I am"+" "+age+" "+"years old"
  }
}
object Student1 {
  def apply( name:String,age:Int): Student1 = new Student1(name,age)
  def main(args: Array[String]): Unit = {
//    Student1.apply.age=25
//    Student1.apply.name="张飞"
    val s3 = Student1("zs",23)
    println(s3.sayHello())
//    val s1 = Student1()
//    s1.name="小花"
//    s1.age=25
//    println(s1.sayHello)
//    val s2 = Student1()
//    s2.name="小明"
//    s2.age=30
//    println(s2.sayHello)
//    println(s1)
//    println(s2)
//    val s3 = new Student1
//    println(s3==s1)
  }
}