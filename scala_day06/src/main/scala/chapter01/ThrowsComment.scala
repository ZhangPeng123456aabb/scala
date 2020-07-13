package chapter01

/**
  * @ProjectName Scala
  * @PackageName chapter01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/13 - 17:28
  */
object ThrowsComment {
  def main(args: Array[String]): Unit = {
    try{
      f11()
    }catch{
      case ex:Exception =>{println("捕获异常")}
    }finally {
      println("ok~~~~~~")
    }
    println("继续执行")
  }
  @throws(classOf[NumberFormatException])
  def f11() ={
    "abc".toInt
  }
}
