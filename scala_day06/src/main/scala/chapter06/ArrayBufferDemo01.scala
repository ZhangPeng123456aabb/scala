package chapter06
import scala.collection.mutable.ArrayBuffer
/**
  * @ProjectName Scala
  * @PackageName chapter06
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/21 - 15:32
  */
object ArrayBufferDemo01 {
  def main(args: Array[String]): Unit = {
    //创建ArrayBuffer
    val arr01 = ArrayBuffer[Any](3,2,5)
    //通过下标访问元素
    println("arr01(1)="+arr01(1))
    //遍历
    for(i<-arr01){
      println(i)
    }
    println(arr01.length)
    println("arr01.hash="+arr01.hashCode())
    arr01.append(90.0,13)
    println("arr01.hashcode="+arr01.hashCode())

    arr01(1)=89
    arr01.remove(0)
    println("****************************")
    for(i<-arr01){
      println(i)
    }
  }
}
