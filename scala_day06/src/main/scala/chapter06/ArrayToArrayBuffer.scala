package chapter06
import scala.collection.mutable._
/**
  * @ProjectName Scala
  * @PackageName chapter06
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/21 - 15:54
  */
object ArrayToArrayBuffer {
  def main(args: Array[String]): Unit = {
    val arr2 = ArrayBuffer[Int]()
    //追加值
    arr2.append(1,2,3)
    println(arr2)

    println("**********************")

    val newArr = arr2.toArray
    println(newArr)

    println("***********************")

    val newArr2 = newArr.toBuffer
    newArr2.append(123)
    println(newArr2)
  }
}
