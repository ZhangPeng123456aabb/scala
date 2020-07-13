package function

/**
  * 递归使用
  */
object Function04 {
  def main(args: Array[String]): Unit = {
    //递归方式：求1~100之和
    println(m1(100))
    //阶乘 1*2*3*4......n
    println(m2(5))
  }
  def m1(n:Int):Int={
    var result=0
     if(n>0){
       result=n+m1(n-1)
     }
    result
  }
  def m2(n:Int):Int={
    var result=1
    if(n>0){
      result=n*m2(n-1)
    }
    result
  }
}
