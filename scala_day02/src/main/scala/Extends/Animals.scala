package Extends
trait Animals {
  //只有方法的声明，没有实现
  //等价于void eat();
  def eat():Unit
  def sleep(name:String):String={
    name+"在睡觉睡觉......."
  }
}
//如果当前类Dog没有父类 extends 特质
class Dog1 extends Animals{
  //等价于实现接口中的public void eat()
  override def eat(): Unit = println("dog eat shit")
}
object AnimalsDemo{
  def main(args: Array[String]): Unit = {
    val dog = new Dog1
    dog.eat()
    println(dog.sleep("小黑黑"))
  }
}
