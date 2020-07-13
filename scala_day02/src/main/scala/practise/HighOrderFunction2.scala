package practise

object HighOrderFunction2 {
  def main(args: Array[String]): Unit = {
    //匿名函数
    val f = (x:Int,y:Int)=>x+y
    println(sum(1,2,f))
  }
  def sum(x:Int,y:Int,f:(Int,Int)=>Int)={
    f(x,y)
  }
}
