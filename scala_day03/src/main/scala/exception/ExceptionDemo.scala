package exception

import java.io.IOException

object ExceptionDemo {
  /**
    * scala异常分类和java一样的
    * Throwable
    *    |- Error
    *    |- Exception
    *    |- 未检查异常
    *    |- 已检查异常
    *
    * 异常处理的方式：
    *    - 消极处理： throw 异常类 没有throws
    *    - 积极处理： try{...}catch(){...}finally{...}
    */
  def main(args: Array[String]): Unit = {
    try {
      println("start")
      throw new IOException
      println("end")
    } catch {
     //模式匹配
      case e1:RuntimeException=>println("runtime exception")
      case e2:Exception=>println("exception")
      case _=>println("other")//如果以上声明的两种异常不匹配，则进入最后一个
    }finally {
      println("释放资源")
    }
  }
}
