package objects

class Dog {
  var name:String=""
  private var id:Int=Dog.increment()
}
object Dog{
  var id:Int=0
  def increment():Int={
    id+=1
    id
  }
  def main(args: Array[String]): Unit = {
    val dog1 = new Dog
    val dog2 = new Dog
    println(dog1)
    println(dog2)
    println(dog1.id)
    println(dog2.id)
  }
}