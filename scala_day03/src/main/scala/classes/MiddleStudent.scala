package classes

class MiddleStudent {
  def playGame()={
    val person = new Person
    //注意：protected 成员 访问的范围仅限在伴生类、伴生对象、子类
    //println(person.name)//并级的关系不成立 error
    println(person.age)
  }
}
object MiddleStudent{
  def main(args: Array[String]): Unit = {
    val studetn = new MiddleStudetn
    studetn.playGame()
  }
}
