package cycle

object YieldDemo {
  //将遍历产生的结果进行计算，放到缓冲区中，遍历结束后，返回一个集合
  def main(args: Array[String]): Unit = {
//    val result = for(n<- 1 to 10 if n%2==1 if n!=5 )yield n*2
//    println(result)
    val result = for {n<- 1 to 10 if n%2 == 0}yield n
    println(result)
  }
}
