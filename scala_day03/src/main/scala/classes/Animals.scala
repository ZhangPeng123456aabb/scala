package classes

trait Animals {
 def eat():Unit
  def sleep(name:String):String={
    s"$name,睡觉觉"
  }
}
class Dog extends Animals{
  override def eat(): Unit = println("Dog eat shit")
}
object AnimalsDemo{
  def main(args: Array[String]): Unit = {
    val dog = new Dog
    dog.eat()
   println(dog.sleep("阿旺"))
  }
}