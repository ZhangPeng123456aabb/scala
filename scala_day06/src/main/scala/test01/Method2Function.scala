package test01

/**
  * @ProjectName Scala
  * @PackageName test01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/13 - 13:59
  */
object Method2Function {
  def main(args: Array[String]): Unit = {
    val dog = new Dog
    println(dog.sum(10,30))
    //方法转成函数
    val f1 = dog.sum _
    println("f1=" + f1)
    println("f1="+f1(50,60))
  }
}
class Dog{
  //方法
  def sum(n1:Int,n2:Int):Int={
    n1+n2
  }
}
