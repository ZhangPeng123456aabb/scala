package test01
import util.control.Breaks._
/**
  * @ProjectName Scala
  * @PackageName test01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/13 - 11:14
  */
object mybreak {
  def main(args: Array[String]): Unit = {
    var n = 0
    breakable{
      while(n<=20){
        println(n)
        n += 1
        if(n==18){
          break()
        }
      }
    }
    print("ok~~~~~")
  }
}
