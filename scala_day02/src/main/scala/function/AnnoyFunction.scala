package function

object AnnoyFunction {
  def main(args: Array[String]): Unit = {
    val f3=(x:Int,y:Int,z:Int)=>z*y*z
    println(f3(2,2,2))
    println(sum2(2)(6))
  }
  def sum1(x:Int,y:Int):Int={x+y}
  def sum2(x:Int)=(y:Int)=>x+y
}
