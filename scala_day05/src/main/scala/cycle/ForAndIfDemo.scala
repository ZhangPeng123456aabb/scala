package cycle

object ForAndIfDemo {
  def main(args: Array[String]): Unit = {
    //if守卫
    //传统写法
    for(n <- 1 to 10){
      if(n%2==0 && n>5){
        println(n)
      }
    }
    println("-------------------------")
    //if 守卫
    for(n <- 1 to 10 if n%2==0 && n>5 if n!= 10){
      println(n)
    }
  }
}
