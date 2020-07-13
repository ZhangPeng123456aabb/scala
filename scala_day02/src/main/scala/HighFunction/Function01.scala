package HighFunction
import scala.math._
object Function01 {
 def main(args : Array[String]) : Unit={
//   val num = 3.14
//   val fun = ceil _
//   println(fun(num))
   //===============作为值的函数====================
//   val doubles = Array(3.14,1.42,2.0).map(fun)
//   for(n<- doubles){
//     println(n)
//   }
   //=================匿名函数============================
//   val triple  = (x:Double)=>3*x
//   val long = Array(3.14,1.42,2.0).map(triple)
//   for (elem <- long) {println(elem)}
   //================================================
//   val triple  = (x:Double)=>3*x
//   val long = Array(3.14,1.42,2.0) map{triple}
//    for (elem <- long) {println(elem)}
    val d = fun(5.3)
    println(d)
//   (1 to 9).map("*" *_).foreach(println _)
//   val i = (1 to 9).reduceLeft(_*_)
//   println(i)
   val strings = "Mary has a little lamb".split(" ").sortWith(_.length<_.length)
   for(b<-strings){
     println(b)
   }
 }
//  def valueAtOneQuarter(f:(Double)=>Double)=f(0.25)
//  val quintuple = mulBy(5)
//  quintuple(20)
//  valueAtOneQuarter(x => 3 * x)
//  valueAtOneQuarter(3 * _)
//  val fun = 3 * (_ : Double)
  val fun:(Double)=>Double=3 * _
}
