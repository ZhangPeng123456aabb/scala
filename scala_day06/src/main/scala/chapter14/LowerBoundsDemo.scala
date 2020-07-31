package chapter14

object LowerBoundsDemo {
  def main(args: Array[String]): Unit = {
    biophony(Seq(new Bird,new Bird))
    biophony(Seq(new Animal,new Animal))
    biophony(Seq(new Animal,new Bird))
    //biophony(Seq(new Earth,new Earth)) 错误 //因为Earth不是Animal的子类
  }
  def biophony[T <: Animal](things:Seq[T]) = things.map(_.sound)
}
class Earth{
  /**
    * Earth类
    * 方法
    */
  def sound(): Unit ={
    println("hello !")
  }
}
class Animal extends Earth{
  /**
    * Earth类
    * 方法
    */
  override def sound(): Unit = {
    //重写了Earth的方法sound
    println("animal sound")
  }
}
class Bird extends Animal{
  /**
    * Earth类
    * 方法
    */
  override def sound(): Unit = {
    println("Bird sound")
  }
}