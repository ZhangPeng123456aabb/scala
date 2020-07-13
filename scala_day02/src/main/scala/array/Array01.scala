package array

object Array01 {
  def main(args: Array[String]): Unit = {
    //数组声明
    val a1 = new Array[Int](10)
    val a2 = new Array[String](5)
    //指定数组 元素并初始化
    val a3 = Array[Long](10,20,30)
    val a4 = Array(1.0,2.0,3.3,5.5,6.5)
    println(a1(0))
    println(a2(4))
    println(a3(1))
    println(a4(1))
    //遍历
    for(n<-0 until(a4.length)){
      println(a4(n))
    }
    println("=============================")
    for(n<-a4){
      println(n)
    }
    println("=============================")
    for(b<-a4.indices by(2)){
      println(a4(b))
    }
  }
}
