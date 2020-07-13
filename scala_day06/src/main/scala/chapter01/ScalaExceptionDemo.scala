package chapter01

/**
  * @ProjectName Scala
  * @PackageName chapter01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/13 - 17:11
  */
object ScalaExceptionDemo {
  def main(args: Array[String]): Unit = {
    try{
      val r = 10/0
    }catch{
      //说明
      //1.在scala中只有一个catch
      //2.在catch中有多个case,每个case可以匹配一种异常case es:ArithmeticException
      //3.=>关键符号，表示后面是对该异常的处理代码块
      //4.finally 最终要执行的
      case ex:ArithmeticException =>{
        println("捕获异常了，除数为零的算数异常")
      }
      case ex:Exception =>println("捕获异常")
    }finally{
      //最终要执行的代码
      println("scala finally")
    }
    println("ok,继续执行")
  }
}
