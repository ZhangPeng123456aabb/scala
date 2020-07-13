package function

object Function02 {
  def main(args: Array[String]): Unit = {
    println(sum(1,2,3,4,5))
    println(sum(1))
    println(sum())
  }
  //变长参数列表 n1  n2.....nn
  //语法:变量名:类型*
  def sum(nn:Int*):Int={
    var sum=0
    for(n<- nn){
      sum+=n
    }
    sum
  }
  //注意：如果有多个参数，可变长参数需要写在所有参数之后
  def sum2(default:Int,nn:Int*):Int={
    var sum=0
    for(n <- nn){
      sum+=n
    }
    sum
  }
}

