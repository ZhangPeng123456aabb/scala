package chapter08

object ViewDemo01 {
  def main(args: Array[String]): Unit = {
    def multiple(num:Int):Int={
      num
    }
    //如果这个数，逆序后和原来数相等，就返回true,否则就返回false
    def eq(i:Int):Boolean={
      println("eq 被调用")
      i.toString.equals(i.toString.reverse)
    }

    //说明：没有使用view,常规方式
//    val ViewSquares1 = (1 to 100).filter(eq)
//    println(ViewSquares1)

    //使用view,来完成这个问题
    val ViewSquares2 = (1 to 100).view.filter(eq)
    println(ViewSquares2)
    //遍历
    for(item <- ViewSquares2){
      println("item= "+item)
    }

  }
}
