package cycle

object WhileDemo {
  def main(args: Array[String]): Unit = {
    var sum=0
    var b=100
    while (b>0){sum+=b
      b-=1}
    println(sum)
    val str = readLine("请输入任意整数:")
    var num:Int = str.toInt
    var num2 = 0
    while(num>0){
      num2+=num
      num-=1
    }
    println(num2)
  }

}
