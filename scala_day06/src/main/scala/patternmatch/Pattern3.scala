package patternmatch

/**
  * 模式匹配中的 变量赋值 可以在处理时获取匹配模式的表达是具体值
  */
object Pattern3 {
  def main(args: Array[String]): Unit = {
    val age = readLine("age:").toInt
    age match{
      case a1 if a1>60 =>println("老年人:"+a1)
      case a2 if a2>40 =>println("中年人"+a2)
      case a3 if a3>20 =>println("青年人"+a3)
      case _a4 =>println("未成年人:"+_a4)//default _变量名
    }
  }
}
