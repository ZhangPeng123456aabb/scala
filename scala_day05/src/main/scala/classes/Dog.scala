package classes

class Dog {
  private var privateColor:String="black"
  //属性名和方法名不能相同
  //def color = privateColor
  //手动生成getter/setter方法
  def color=privateColor
  def color_(color:String): Unit ={
    privateColor = color
  }
}
object DogDemo{
  def main(args: Array[String]): Unit = {
    val dog = new Dog
    dog.color_("red")
    println(dog.color)
  }
}