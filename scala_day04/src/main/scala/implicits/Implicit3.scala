package implicits

object Implicit3 {
  implicit var x:Int=0
  def main(args: Array[String]): Unit = {
    //伴生类函数体
    implicit var str:String="Hi"
    import AA._
    var ll:Long=implicitly[Long]
    println(ll)
  }
}
object AA{
  //其它对象中定义了一个 隐式值
  implicit var l:Long=10000L
}
