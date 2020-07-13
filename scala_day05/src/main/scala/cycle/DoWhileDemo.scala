package cycle

object DoWhileDemo {
  def main(args: Array[String]): Unit = {
    //1 ~ n数值之和
    var num = readLine("请输入任意数字: ").toInt
    var sum = 0
    do{
      sum += num
      num -= 1
    }while(num>0)
    println("1 到 N 数值之和"+sum)
  }
}
