package chapter06
import scala.collection.JavaConversions.bufferAsJavaList
import scala.collection.mutable.ArrayBuffer
/**
  * @ProjectName Scala
  * @PackageName chapter06
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/21 - 16:34
  */
object ArrayBufferToJavaList {
  def main(args: Array[String]): Unit = {
    //Scala集合和Java集合互相转换
    val arr = ArrayBuffer("1","2","3","4")
    val javaArr = new ProcessBuilder(arr)
    val arrList = javaArr.command()
    //输出结果:[1, 2, 3, 4]
    println(arrList)

    println("************************")
    //1.asScalaBuffer是一个隐式函数
    import scala.collection.JavaConversions.asScalaBuffer
    import scala.collection.mutable
    //java.util.List ==>Buffer
    val scalaArr:mutable.Buffer[String]=arrList
    scalaArr.append("jack")
    //删除
    scalaArr.remove(0)
    println(scalaArr)
  }
}
