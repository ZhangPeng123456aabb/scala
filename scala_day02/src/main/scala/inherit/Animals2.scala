package inherit

class Animals2 {
  var name:String=""
  def sleep=println("动物在睡觉")
}
class Dog2 extends Animals2{
  //方法覆盖
  override def sleep: Unit = println(s"阿王在睡觉，请${name}不要啊打扰他")
}
object Dog2{
  def main(args: Array[String]): Unit = {
//    val dog = new Dog2
    val dog:Dog2 = new Dog2
    dog.name="阿黑"
    dog.sleep
  }
}