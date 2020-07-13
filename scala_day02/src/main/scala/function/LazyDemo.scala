package function

object LazyDemo {
  def main(args: Array[String]): Unit = {
//    lazy val b1 = scala.io.Source.fromFile(" ")
//    println(b1.mkString)
    println(m2(5))
  }
//  def m1(n:Int):Int={
//    var result = 0
//    if(n>0){
//      result = n+m1(n-1)
//    }
//    result
//  }
  def m2(n:Int):Int ={
  var result = 1
  if (n > 0) {
  result = n*m2(n-1)
}
result
}
}
