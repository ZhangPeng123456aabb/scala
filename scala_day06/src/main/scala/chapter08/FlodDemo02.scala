package chapter08

object FlodDemo02 {
  def main(args: Array[String]): Unit = {
    val list4 = List(1,9)
    def minus(num1:Int,num2:Int):Int={
      num1 - num2
    }
    var i6 = (1 /: list4)(minus)//等价于 list4.foldLeft(1)(minus)
    println("i6= "+i6)//-9

    i6 = (100 /: list4)(minus)//等价于 list4.foldLeft(100)(minus)
    println(i6)//90

    i6 = (list4 :\ 10)(minus)//等价于 list4.foldRight(10)(minus)
    println(i6)//2
  }
}
