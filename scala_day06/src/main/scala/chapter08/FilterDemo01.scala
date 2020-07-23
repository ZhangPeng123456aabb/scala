package chapter08

object FilterDemo01 {
  def main(args: Array[String]): Unit = {
    val names = List("Alice","Bob","Nick")
    /**
      * 选出首字母为A的元素
      */
    val name1 = names.filter(startA)
    println("names= "+names)
    println("name1= "+name1)
  }
  def startA(str:String):Boolean={
    str.startsWith("A")
  }
}
