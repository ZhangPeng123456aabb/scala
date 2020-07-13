package practise

object WhileDemo {
  def main(args: Array[String]): Unit = {
    var a = readLine("请输入一个数字:")
    var a1 = a.toInt
    var sum = 1
    while(a1>0){
      sum*=a1
      a1-=1
    }
    println(sum)
  }
}
