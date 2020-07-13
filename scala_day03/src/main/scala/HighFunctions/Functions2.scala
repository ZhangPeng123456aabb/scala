package HighFunctions

object Functions2 {
  def main(args: Array[String]): Unit = {
    val result = (x:Int,y:Int)=>x+y
    val result1 = sum(1,2,result)
    println(result1)
  }
  def sum(x:Int,y:Int,f:(Int,Int)=>Int)=x+y
}
