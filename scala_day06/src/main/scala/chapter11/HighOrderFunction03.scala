package chapter11

object HighOrderFunction03 {
  def main(args: Array[String]): Unit = {
    /**
      * 说明:
      * 1.minusxy是高阶函数，因为它返回匿名函数
      * 2.返回的匿名函数(y:Int) => x-y
      * 3.返回的匿名函数可以使用变量接收
      * @param x
      * @return
      */
    def minusxy(x:Int)={
      (y:Int) => x-y
    }
    val f1 = minusxy(3)
    val result3 = minusxy(3)(5)
    println("f1的类型= "+f1)
    println("result3= "+result3)
  }
}
