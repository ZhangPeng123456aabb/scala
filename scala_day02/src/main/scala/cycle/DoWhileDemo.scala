package cycle

object DoWhileDemo {
  def main(args: Array[String]): Unit = {
    //1到100数值之和
   var num =  readLine("请输入任意整数:").toInt
    var sum = 0
    do {
      sum+=num
      num-=1
    }while(num>0)
    println("1到N数值之和"+sum)
  }
}
