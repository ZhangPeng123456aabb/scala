package chapter14

object ViewBoundsDemo01 {
  def main(args: Array[String]): Unit = {
    //使用了隐式转换
    val compareComm01 = new CompareComm(1,20)
    println(compareComm01.greater)
  }
}
/**
  * 说明:
  * 1.T <% Comparable[T] 说明T是Comparable子类型
  * 2.T <% Comparable[T] 和 T <: Comparable[T] 区别就是视图界定支持隐式转换
  * 3.视图界定不但支持以前上界的写法，同时支持简写
  */
class CompareComm[T <% Comparable[T]](obj1: T,obj2: T){
  def greater = if(obj1.compareTo(obj2)>0) obj1 else obj2
}