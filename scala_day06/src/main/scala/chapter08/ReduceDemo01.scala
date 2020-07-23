package chapter08

object ReduceDemo01 {
  def main(args: Array[String]): Unit = {
    /**
      * 使用简化的方式来计算list集合的和
      */
    val list = List(1,20,30,4,5)
    val res = list.reduceLeft(sum)
    println("res= "+res)//60
  }
  def sum(a:Int,b:Int):Int={
    println("sum被调用")//递归调用4次
    a + b
  }
}
