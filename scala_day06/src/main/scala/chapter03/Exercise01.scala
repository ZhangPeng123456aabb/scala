package chapter03

/**
  * @ProjectName Scala
  * @PackageName chapter03
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/16 - 14:23
  */
object Exercise01 {
  def main(args: Array[String]): Unit = {
    //type 可以用于给类型取别名
    type MyInt = Int  //给Int 取了别名
    val num1:MyInt = 888
    println("num1= "+num1)
  }
}
