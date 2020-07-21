package chapter06
import scala.collection.mutable.ArrayBuffer
/**
  * @ProjectName Scala
  * @PackageName chapter06
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/21 - 15:19
  */
object ArrayDemo01 {
  def main(args: Array[String]): Unit = {
    /**
      * 说明:
      * 1.使用的是object Array 的apply
      * 2.直接初始化数组，这是因为你给了整数和" ",设个数组的泛型就是Any
      * 3.遍历方式一样
      */
    var arr02 = Array(1,3,"xxx")
    for(i <- arr02){
      println(i)
    }

    //使用下标遍历
    for(index <- 0 until arr02.length){
      println(index)
    }
  }
}
