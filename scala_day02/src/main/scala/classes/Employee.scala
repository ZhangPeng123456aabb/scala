package classes

object Employee {
  def main(args: Array[String]): Unit = {
    val e1  = new Employee1
    e1.name="隔壁老王"
    val e2 = new Employee1
    e2.name="楼下小张"
    println(e1)
    println(e2)

    println(e1.sayHello)
    println(e2.sayHello)
    println(e1.name)
  }
}
class Employee1{
  var id : Int = 0
  var name :String = ""
  def sayHello:String={
    s"Hello:$name"
  }
}