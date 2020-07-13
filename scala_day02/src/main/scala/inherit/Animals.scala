package inherit

class Animals {
  var name:String=""
}
class Dog extends Animals{
  def run:String=s"Hello,我在田野里边，看着$name,努力地奔跑"
}
object Dog{
  def main(args: Array[String]): Unit = {
    val dog = new Dog
    dog.name="小飞飞"
    println(dog.run)
  }
}