package implicits

object ImplicitParams {
  implicit var num:Int=10
  def main(args: Array[String]): Unit = {

    println(sum(10))

    println(sayHi)
  }
  def sum(x:Int)(implicit y:Int)=x+y

  //def sum1(x:Int,implicit y:Int)=x+y//普通函数无法使用隐式函数
  implicit val str:String = "Hello"
  def sayHi(implicit name:String):String={
    s"Hello,$name"
  }
//def sum3(x:Int)(implicit y:Int)(implicit z:String)=x+y+z//不可以，隐式函数只能在柯里化函数中使用
}
