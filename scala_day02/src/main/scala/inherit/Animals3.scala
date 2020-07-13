package inherit

class Animals3 {
  var name:String=""
  def sleep=println("动物正在睡觉")//fianl修饰的方法 是不能覆盖的
}
class Dog3 extends Animals3{
  def run:String=s"二哈：$name,在愉快地田野里奔跑"

  override def sleep: Unit = println("二哈，正在做着他的美梦")
}
object Dog3{
  def apply(): Dog3 = new Dog3()
  def main(args: Array[String]): Unit = {
    val dog = new Dog3
    val dog2 = Dog3()
    println(dog==dog2)
    println(dog.isInstanceOf[Animals3])//判断类型是否兼容
    println(dog.asInstanceOf[Animals3])//类型转换 Animals a = (Animals) dog
    println(dog.getClass==classOf[Dog3])//判断真实类型是否匹配
  }
}