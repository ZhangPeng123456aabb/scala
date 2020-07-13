package chapter01

/**
  * @ProjectName Scala
  * @PackageName chapter01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/13 - 15:56
  */
/**
  * 懒加载
  */
object LazyDemo {
  def main(args: Array[String]): Unit = {
    lazy val res = sum(10,20)
    println("************************")
    println("res= "+res)
  }
  //sum函数,返回和
  def sum(n1:Int,n2:Int):Int={
    println("sum()执行了..")
    return n1+n2
  }
}
