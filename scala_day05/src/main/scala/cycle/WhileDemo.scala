package cycle

/**
  * while
  * while(boolean){循环体}
  */
object WhileDemo {
  def main(args: Array[String]): Unit = {
    var a = 10
//    while(a>0){
//      println(s"$a")
//      //注意事项：scala中没有++ --
//      //a-=1
//      a-=1
//    }
    // 1  2  3  4.....数值之和
//    var b = 100
//    var sum = 0
//    while(b>0){
//      sum+=b
//      b-=1
//    }
//    println(s"1~100数值之和=$sum")
    //读取控制台任意整数 求1~任意整数数值之和
    val str = readLine("请输入任意整数：")
    var num = str.toInt
    var num2 = 0
    while(num>0){
      num2+=num
      num-=1
    }
    println(s"1~n数值之和="+num2)
  }
}
