package function

object Function03 {
  def main(args: Array[String]): Unit = {
    println(m1())
    println(m1("welcome"))
    println(m2("Hello",10,88.88))
    println(m2())
    println(m2(arg3=99.99,arg1="Pengpeng",arg2=80))
  }
  def m1(arg1:String="Hello"):String={
    arg1
  }
  def m2(arg1:String="ZhangPeng",arg2: Int=10,arg3: Double=66.66): String ={
    arg1+ "\t" +arg2+ "\t" +arg3
  }
}
