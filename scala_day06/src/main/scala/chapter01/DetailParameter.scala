package chapter01

/**
  * @ProjectName Scala
  * @PackageName chapter01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/13 - 15:22
  */
object DetailParameter {
  def main(args: Array[String]): Unit = {
    mysqlCon()
    println("*************************")
    mysqlCon("127.0.0.1",7777)
    println("*************************")
    //带名参数
    mysqlCon(user = "Tom",pwd = "123")
  }
  def mysqlCon(add:String="localhost",port:Int=3306,user:String="root",pwd:String="root"):Unit={
    println("add="+add)
    println("port="+port)
    println("user="+user)
    println("pwd="+pwd)
  }
}
