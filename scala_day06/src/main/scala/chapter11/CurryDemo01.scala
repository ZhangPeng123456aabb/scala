package chapter11

object CurryDemo01 {
  def main(args: Array[String]): Unit = {
    //这是一个函数，可以接收一个两个参数，比较是否相等
    def eq(s1:String,s2:String): Boolean ={
      s1.equals(s2)
    }
    implicit class TestEq(s:String){
      /**
        * 体现了将比较字符串的事情，分解成两个任务完成
        * 1.checkEq完成转换大小写
        * 2.f函数完成比较任务
        * @param ss
        * @param f
        * @return
        */
      def checkEq(ss:String)(f:(String,String)=>Boolean):Boolean={
        f(s.toLowerCase,ss.toLowerCase)
      }
    }
    val str = "hello"
    println(str.checkEq("HELLO")(eq))
    //简写形式
    println(str.checkEq("HELLO")(_.equals(_)))
  }
}
