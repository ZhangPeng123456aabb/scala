package chapter05

/**
  * @ProjectName Scala
  * @PackageName chapter05
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/20 - 17:38
  */
object ImplicitDemo1 {
  def main(args: Array[String]): Unit = {
    //编写一个隐式转换Double -> Int
    implicit def f1(num:Double):Int={
      num.toInt
    }
    val num : Int = 3.5
    val num1 : Int = 6.7
    System.out.println(num)
  }

}
