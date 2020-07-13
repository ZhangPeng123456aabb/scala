package array

object Array01 {
  def main(args: Array[String]): Unit = {
    //数组的声明
    //a1数组的长度为10 泛型为Int
    val a1 = new Array[Int](10)
    val a2 = new Array[String](5)
    //指定数组 元素的初始化
    val a3 = Array[Long](10,20,30)
    val a4 = Array(1.0,2.0,3.0)
    //使用：长度 length 下标 index【0~length-1】
    //访问数组元素使用 变量名(下标)
    //数组默认值：整数为 0 小数为 0.0 boolean false null
    println(a1(0))
    println(a2(4))
    println(a3(1))
    println(a4(2))
    println("------------------------")
    //遍历 传统方式
    for(n <-0 until (a4.length,2)){
      println(a4(n))
    }
    println("========================")
    for(n <- a4){
      println(n)
    }
    println("=========================")
    for(n <- a4.indices by 2){
      //Range(0,1,2)
      println(a4(n))
    }
  }
}
