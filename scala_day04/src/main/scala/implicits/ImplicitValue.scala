package implicits

object ImplicitValue {
  //隐式值声明 对象内部或者作用域
  implicit val name:String="Hello"
  implicit  val sex:Boolean=false
  def main(args: Array[String]): Unit = {
    //使用隐式值
    val newValue = implicitly[String]
    val newValue2=implicitly[Boolean]
    println(newValue)
    println(newValue2)
  }
}
