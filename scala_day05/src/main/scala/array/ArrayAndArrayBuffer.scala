package array

object ArrayAndArrayBuffer {
  def main(args: Array[String]): Unit = {
    //定长数组转为可变数组
    val a1 = Array(1,2,3,4,5)
    val a2 = a1.toBuffer
    println(a2)

    a2+=6
    //可变数组转为定长数组
    val a3 = a2.toArray
    println(a3.mkString(","))
  }
}
