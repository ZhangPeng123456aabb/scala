package practise

object HighOrderFunction {
  def main(args: Array[String]): Unit = {
    //1.将函数作为值使用
    def sum(x:Int,y:Int):Int=x+y
    def multi(x:Int,y:Int,z:Int):Int=x*y*z
    //函数对象
    val s = sum _
    println(s)
    //function 0~22 23 个函数对象
    val s2 = new Function2[Int,Int,Int] {
      //伴生类对象 用以创建伴生类
      override def apply(v1: Int, v2: Int): Int =v1+v2
    }
    println(s2)

    val s3 = multi _
    val s4 = new Function3[Int,Int,Int,Int] {
      override def apply(v1: Int, v2: Int, v3: Int): Int = v1*v2*v3
    }
    println(s3)
    println(s4)
  }
}
