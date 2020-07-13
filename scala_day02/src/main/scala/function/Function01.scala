package function

object Function01 {
  //主函数声明
  def main(args: Array[String]): Unit = {
    println(sum(1,3))
    println(sum1(1,3))
    println(sum3(1,3))
    println(sum4(1,3))
    println(sayHello("zs"))
    println(getInitNum)//没有参数的函数，括号可以省略
  }
  //普通函数
  def sum(x:Int,y:Int):Int = {
    x+y
  }
  //：返回值类型可以省略，代码块中的最后一行表达式结果
  def sum1(x:Int,y:Int) ={
    x+y+1.0
  }
  def sum3(x:Int,y:Int):Unit={
    x+y+1.0
  }
  def sum4(x:Int,y:Int){
    println(x+y+1.0)
  }
  def sayHello(name : String)={
    "Hello:"+name
  }
  def getInitNum():Int={
    100
  }
}
/**
  * 有返回值的函数，：函数名（参数列表）返回值类型：{}
  */