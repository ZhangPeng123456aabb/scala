package cycle

/**
  * java for:
  * for(初始值；循环条件；迭代条件)
  * {
  * //循环体
  * }
  * scala for:
  *   for(临时变量<-集合、数组、Range){
  *   循环体
  *   }
  */
object ForDemo {
  def main(args: Array[String]): Unit = {
    //    var sum = 0
    //    for(n <- 1 until 100){
    //      sum+=n
    //    }
    //    println(sum)
    //  }
    //  def main(args: Array[String]): Unit = {
    //    var sum = 0
    //    for(n <- 1 to  100){
    //      sum+=n
    //    }
    //    println(sum)
    //  }
//    for (m<- 1 to 9){
//      for(n<- 1 to m){
//        print(s"$m*$n="+(m*n)+"\t")
//      }
//      println()
//    }
    for(m<- 1 to 9;n<- 1 to m ){
      print(s"$m*$n="+(m*n)+"\t")
      if(m==n)println()
    }
  }
}
