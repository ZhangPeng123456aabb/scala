package chapter07

object FlatMapDemo01 {
  def main(args: Array[String]): Unit = {
    val names = List("Alice","Bob","Nick")
    //函数的扁平化处理
    val name1 = names.flatMap(upper)
    println("name1= "+name1)//输出结果:name1= List(A, L, I, C, E, B, O, B, N, I, C, K)
  }

  /**
    * 字母转大写
    * @param s
    * @return
    */
  def upper(s:String):String={
    s.toUpperCase
  }
}
