package cycle

/**
  * java for:
  * for(初始值;循环条件;迭代条件){
  * //循环体
  * }
  * scala for:
  * for(临时变量 <- 集合、数组、Range){
  * //循环体
  * }
  */
object ForDemo {
  def main(args: Array[String]): Unit = {
    //1~100之和
//    var sum = 0
//    for(n<- 1 to 100){
//      sum+=n
//    }
//    println(sum)
    //1~99之和
//    var sum = 0
//    for(n <- 1 until 100){
//      sum += n
//    }
//    println(sum)
    // 1 3 5 7 9数值之和
//    for(n <- 1 to 10 by 2){
//      println(n)
//    }
    //传统写法：嵌套for循环(多重for) 多重for用";"分割
    for(n <- 1 to 9;m <- 1 to n){
      print(s"$m*$n="+(n*m)+"\t")
      if(n==m)println
    }
  }
}
