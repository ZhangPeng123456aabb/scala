package chapter08

object FoldDemo01 {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4)
    def minus(num1:Int,num2:Int):Int={
      num1 - num2
    }

    /**
      * 说明
      * 1.list.foldLeft(5)(minus)理解成 list(5,1,2,3,4) list.reduceLeft(minus)
      *
      */
    println(list.foldLeft(5)(minus))//函数的柯里化
    println(list.foldRight(5)(minus))
  }
}
