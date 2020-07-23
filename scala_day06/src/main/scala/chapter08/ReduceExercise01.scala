package chapter08

object ReduceExercise01 {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5)
    def minus(num1:Int,num2:Int):Int={
      num1 - num2
    }
    println(list.reduceLeft(minus))//输出 -13
    println(list.reduceRight(minus))//输出 3
    println(list.reduce(minus))

    println(list+"最小值为:"+list.reduce(compareMin))
  }
  def compareMin(n1:Int,n2:Int):Int={
    if(n1 > n2) n2 else n1
  }
}
