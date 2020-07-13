package practise

object Array01 {
  def main(args: Array[String]): Unit = {
    //数组声明
    //a1数组的长度为10 泛型为Int
    val a1 = new Array[Int](10)
    val a2 = new Array[String](5)
    //指定数组 元素并初始化
    val a3 = Array[Long](10,20,30)
    val a4 = Array(1.0,2.0,3.0)
    println(a1(0))
    println(a2(1))
    println(a3(0))
    println(a4(1))
    println("===========================")
    //遍历 传统方式
    for(n <-0 until(a4.length,2)){
      println(a4(n))
      println("==========================")
      for(n1 <- a4){
        println(n1)
      }
      println("==========================")
      for(n<- a4.indices by(2)){
        println(a4(n))
      }
    }
  }
}
