package classes

object User {
  def main(args:Array[String]):Unit={
    val employee = new Employee()
    employee.name="隔壁老王"
    val employee1 = new Employee
    employee1.name="落下校长"
//    println(employee)
//    println(employee1)
    println(employee.sayHello)
    println(employee1.sayHello)
  }
}
class Employee{
  var id :Int=0
  var name :  String = ""

  def sayHello:String ={
    s"Hello:$name"
  }
}