package test01
import scala.io.StdIn
/**
  * @ProjectName Scala
  * @PackageName test01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/12 - 19:30
  */
object StdIn1 {
  def main(args: Array[String]): Unit = {
    print("姓名")
    val name = StdIn.readLine()
    print(name)

    cat.sayHello()
    cat.sayOk()
  }
}
object cat extends AAA{
  def sayHello():Unit={
    println("小狗汪汪叫....")
  }
}
trait AAA{
  def sayOk():Unit={
    println("AAA sayOk")
  }
}
