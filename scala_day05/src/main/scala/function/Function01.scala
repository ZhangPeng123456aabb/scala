package function

object Function01{
  def main(args: Array[String]): Unit = {
    println(sum1(1,2))
    println(sum2(1,2))
    sum3(1,2)
    sum4(2,3)
    println(sayHello("zs"))

    println(getInitNum())
    println(getInitNum)
  }
  //普通函数
  def sum1(x:Int,y:Int):Int={
    x+y
  }
  //返回值类型  可以省略，代码块中的最后一行表达式结果会作为函数的返回值
  def sum2(x:Int,y:Int)=x+y+1.0
  //此函数无返回值，严格意义来说Unit的值为()
  def sum3(x:Int,y:Int): Unit ={
    println(x+y+1.0)
  }
  //此函数无返回值，简化写法
  def sum4(x:Int,y:Int): Unit ={
    println(x+y+1.0)
  }
  def sayHello(name:String)={
    "Hello:"+name
  }
  def getInitNum():Int={
    100
  }
}