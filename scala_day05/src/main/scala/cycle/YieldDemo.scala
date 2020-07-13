package cycle
//将便利产生的数据进行计算后 先缓存，在遍历结束后返回一个新的集合
object YieldDemo {
  def main(args: Array[String]): Unit = {
//   val result =  for(n <- 1 to 5)yield n * 3
//    println(result)
    val result = for{n <- 1 to 10 if n%2==0}yield n
    println(result)
  }
}
