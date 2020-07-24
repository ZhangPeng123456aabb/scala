package chapter10

object CaseClassDemo01 {
  def main(args: Array[String]): Unit = {
    for(amt <- Array(Dollar(1000.0),Currency(1000.0,"RMB"),NoAmount)){
      val result = amt match{
        case Dollar(v) => "$" + v
        case Currency(v,u) => v + " " + u
        case NoAmount => ""
      }
      println(amt + ":" +result)
    }
  }
}
abstract class Amount
case class Dollar(value:Double) extends Amount //样例类
case class Currency(value:Double,unit:String) extends Amount//样例类
case object NoAmount extends Amount //样例类