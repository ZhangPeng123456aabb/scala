package chapter05

/**
  * @ProjectName Scala
  * @PackageName chapter05
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/21 - 11:52
  */
object ImplicitDemo03 {
  def main(args: Array[String]): Unit = {
    //隐式值
    implicit val str1:String = "jack~"
    //implicit name:String:name 就是隐式参数
    def hello(implicit name:String):Unit={
      println(name+" hello")
    }
    hello
  }
}
