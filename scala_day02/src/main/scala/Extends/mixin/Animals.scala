package Extends.mixin

trait Animals {
 println("animals")
  def eat():Unit
}
trait Sheep extends Animals{
  println("sheep")

  //实现父trait中的抽象方法 为具体方法
  override def eat(): Unit = println("食草")
  def run = println("在愉快的奔跑！")
}
//扩展当前类的功能 但又不想破坏类的结构或者声明
//传统方式
//class Horse extends Animals with Sheep
//Override def eat():Unit=println("吃饲料")
trait Monkey extends Animals{
  println("Monkey")

  override def eat(): Unit = println("吃桃子和香蕉")
  def sleep()=println("睡觉....")
}

