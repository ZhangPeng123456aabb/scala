package classes

object DogDemo {
  def main(args: Array[String]): Unit = {
    val dog = new Dog
    dog.color_("yellow")
    println(dog.color)
  }
}
class Dog{
  private var privateColor:String="black"
  //属性名和方法名不能相同
  //手动生成getter和Setter方法
  def color = privateColor
  def color_(color: String):Unit={
    privateColor=color
  }
}