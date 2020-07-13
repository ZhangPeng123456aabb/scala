package cycle

import scala.io.Source

object BreakDemo {
  def main(args: Array[String]): Unit = {
    //scala中没有break和continue,如果终止循环，需要调用breaks中的break和breakable
//    Breaks.breakable{
//      for (n<- 1 to 10){
//        if(n==5){
//          //跳出循环
//          Breaks.break()
//        }
//        println(n)
//      }
//    }
    val source = Source.fromFile("I:\\中文\\test1.txt","UTF-8")
//    val lineIterator = source.getLines()
    val contents = source.mkString
    println(contents)
    source.close()
  }
}
