package classes

class Person {
  //受保护的成员
  protected var name : String = ""
  var age : Int = 0
  val add : String  = ""
  protected[this] val sex : Boolean = false
  def run(): Unit ={
    println(s"$sex")
  }
}
class MiddleStudetn extends Person{
  age = 45
//  override val add: String = ""
  def playGame()=println(s"small student $name,$age like playing 王者荣耀")

  override def run(): Unit = println(s"$sex")
}
object Person{
  def main(args: Array[String]): Unit = {
    val s1 = new MiddleStudetn
    s1.name="张三"
    s1.playGame()
    val person = new Person
    person.name="lisi"
    println(person.name)

  }
}