package classes

class Employee {
  var id:Int=0
  var name:String=""
  def sayHello:String={
    s"Hello:$name"
  }
}
object EmployeeDemo{
  def main(args: Array[String]): Unit = {
    val e1 = new Employee
    e1.name="隔壁老王"
    val e2 = new Employee
    e2.name="落下小张"
    println(e1)
    println(e2)
    println("===================")
    println(e1.sayHello)
    println(e2.sayHello)
    println(e1.name)
  }
}