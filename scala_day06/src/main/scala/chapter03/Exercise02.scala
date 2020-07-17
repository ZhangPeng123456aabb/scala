package chapter03


/**
  * @ProjectName Scala
  * @PackageName chapter03
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/16 - 14:32
  */
object Exercise02 {
  def main(args: Array[String]): Unit = {
    println(Suits)
    println(Suits.isRed(Suits.Heart))
    println(Suits.isRed(Suits.Spade))
  }
}

object Suits extends Enumeration{
  type Suits = Value //给value类型取别名
  val Spade = Value("1")
  val Club = Value("2")
  val Heart = Value("3")
  val Diamond = Value("4")

  override def toString(): String = {
    Suits.values.mkString(",")
}
  def isRed(card:Suits)={
    card ==Heart || card==Diamond
  }
}
