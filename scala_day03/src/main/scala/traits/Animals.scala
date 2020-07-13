package traits
//注意：java的接口可以当着scala的traits使用
//traits Animals extends Serializable with Cloneable
trait Animals {
  //只有方法的声明，没有实现
  //等价于void eat();
  def eat():Unit
  def sleep():Unit={
    //带有方法的具体实现
    println("animals sleep~~~~~")
  }
}
//如果当前类Dog没有父类 extends 特质
class Dog extends Animals{
  override def eat(): Unit = println("dog eat shit")
}
object AnimalsDemo{
  def main(args: Array[String]): Unit = {
    val dog = new Dog
    dog.eat()
    dog.sleep()
  }
}
