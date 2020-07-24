package chapter09

object MatchObject {
  def main(args: Array[String]): Unit = {
    //模式匹配
    val number:Double = 6.0
    number match{
      /**
        * 说明
        * 1.当匹配case Square(n) 的运行机制
        * 2.调用Square 的unapply(x:Double),x的值就是number
        * 3.如果对象提取器unapply(x:Double) 返回就是Some(6),则表示匹配成功，同时将6赋予Square(n) 的n
        */
      case Square(n) => println("匹配成功 n= "+n)
      case _ => println("nothing matched")
    }
  }
}
object Square{
  /**
    * 说明:
    * 1.unapply 方法是对象提取器
    * 2.接收z:Double类型
    * 3.返回类型是Option[Double]
    * 4.返回值是 Some(math.sqrt(z)) 返回z的开平方值，并放入到Some(x)
    */
  def unapply(x:Double): Option[Double] = {
    println("unapply方法被调用 x = "+x)
    //Some(math.sqrt(x))//匹配成功
    None//匹配失败
  }

  def apply(x:Double): Double = x * x
}