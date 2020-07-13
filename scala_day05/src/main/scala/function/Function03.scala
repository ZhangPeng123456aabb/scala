package function

object Function03 {
  def main(args: Array[String]): Unit = {
    println(m1("welcome"))
    //无给定值，使用默认值
    println(m1())
    //给定值，使用给定值
    println()
    println(m2())
    //给定值，使用给定值
    println(m2("welcome",20,100.0))
    //部分参数使用给定值，另一部分参数使用默认值
    println(m2(arg2 = 100))
    //带名参数
    println(m2(arg3 = 88.88,arg1 = "Hi",arg2 = 1000))
  }
  //默认值函数
  def m1(arg1: String="Hello"):String={
    arg1
  }
  //默认值函数
  //函数不支持重载，只有方法支持重载
  def m2(arg1: String="Hello",arg2: Int=10,arg3: Double=10.0):String={
    arg1+"\t"+arg2+"\t"+arg3
  }
}
