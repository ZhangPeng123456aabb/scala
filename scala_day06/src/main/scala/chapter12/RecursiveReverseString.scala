package chapter12

object RecursiveReverseString {
  def main(args: Array[String]): Unit = {
    val str = "Hello"
    println(reverse(str))
  }
  //使用递归完成对字符串的翻转
  def reverse(xs:String):String=
    if(xs.length == 1)//如果长度为1，就返回
       xs
    else
       reverse(xs.tail)+xs.head
}
