package array

object Array02 {
  def main(args: Array[String]): Unit = {
    val a1=Array(1,5,4,89,60,25,77,33)
//    for(n<- 0 until a1.length;m<- 0 until a1.length-n-1){
//      var temp = 0
//      if(a1(m)>a1(m+1)){
//        temp = a1(m)
//        a1(m)=a1(m+1)
//        a1(m+1) = temp
//      }
//    }
//    a1.foreach(println)
    for(n <- 0 to a1.length){
      println(n)
    }
    println("====================")
    for(n <- 0 until a1.length){
      println(n)
    }
  }
}
