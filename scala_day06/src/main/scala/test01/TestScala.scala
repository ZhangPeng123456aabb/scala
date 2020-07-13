package test01

/**
  * @ProjectName Scala
  * @PackageName test01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/12 - 14:28
  */
object TestScala {
  def main(args: Array[String]): Unit = {
//    var num = 10
//    println(num.isInstanceOf[Int])
//
//    var a = 1.7
//    var b = 1.7f
//    a = b.toFloat
//    print(a)

//    println(scala.math.sqrt(4))
//    println(scala.math.pow(2,1024).toFloat)
//    println("Hello".take(4))
//    val ++ = 90
//    print(++)

//    val `true` = "Hello"
//    print("res="+`true`)
    val a = 97
    printf("信息统计 还有 %d 个星期,零 %d 天 ",a/7,a%7)

    val huashi = 232.5
    val sheshi = 5.0 / 9 * (huashi - 100)
    println("\n对应的摄氏温度"+sheshi.formatted("%.2f"))

  }
  def sayHello:Nothing={
    return throw  new Exception("异常中断")
  }
}
