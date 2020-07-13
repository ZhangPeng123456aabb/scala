package implicits

object Implicit4 {
  def main(args: Array[String]): Unit = {
    implicit var x:Int=10
    //println(sum(10))
    def sum1(x:Int)(implicit y:Int)=x+y
    println(sum1(20))
  }
}
object BB{
  def sum(x:Int)(implicit y:Int):Int={
    x+y
  }
}