package practise

object Function01 {
  def main(args: Array[String]): Unit = {
    println(sum1(4,5))
    println(sum2(4,5))
    println(sum3(4,5))
    println(sum4(4,5))
    println(sayHello("张朋"))
    println(getInitNum())
    println(getInitNum)
  }
  def sum1(x:Int,y:Int):Int={
    x+y
  }
  def sum2(x:Int,y:Int)={
    x+y+1.0
  }
  def sum3(x:Int,y:Int):Unit={
    println(x+y+1.0)
  }
  def sum4(x:Int,y:Int){
    println(x+y+1.0)
  }
  def sayHello(name:String)={
    "Hello"+name
  }
  def getInitNum():Int={
    100
  }
}
