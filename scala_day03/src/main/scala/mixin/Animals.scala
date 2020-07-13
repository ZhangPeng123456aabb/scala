package mixin

trait Animals {
  println("Animals")
 def eat():Unit
//  def sleep
}
trait Sheep extends Animals{
  println("Sheep")
  override def eat(): Unit = println("羊吃草")
  def run=println("羊在奔跑")
//  def sleep
}
trait Monkey extends Animals{
  println("Monkey")
  override def eat(): Unit = println("吃桃和香蕉")
}
class Person extends Animals{
  println("Person")
  override def eat(): Unit = println("人在吃饭")
  def sing=println("人在唱歌")
}
object Person{
  def main(args: Array[String]): Unit = {
//    val person = new Person with Sheep {
//      override def sleep: Unit = println("睡觉觉哦~~")
//    }
    val person = new Person with Sheep with Monkey
//    person.eat()

//    person.eat()
//    person.sing
//    person.run
//    person.sleep

    /**
      * 初始化时，从左向右进行（scala重复初始化已经存在的父特质/父抽象类）
      * 当前类和混入特质/抽象类有相同的方式时，优先调用右方特质的方法（方向，从右向左）
      */
  }
}

