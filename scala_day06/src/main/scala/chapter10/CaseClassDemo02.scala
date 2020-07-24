package chapter10

object CaseClassDemo02 {
  def main(args: Array[String]): Unit = {
    val amt = new Currency2(3000.0,"RMB")
    val amt2 = amt.copy()//克隆，创建的对象和amt的属性一样
    println("amt2.value="+amt2.value+" "+"amt2.unit="+amt2.unit)
    println(amt2)

    val amt3 = amt.copy(8000.0)
    println("amt3="+amt3)
    val amt4 = amt.copy(unit = "$")
    println(amt4)
  }
}
abstract class Amount2
case class Dollar2(value:Double) extends Amount2 //样例类
case class Currency2(value:Double,unit:String) extends Amount2//样例类
case object NoAmount2 extends Amount2 //样例类