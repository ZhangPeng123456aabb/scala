package HighFunctions

object Function03 {
  def main(args: Array[String]): Unit = {
    val a1 = Array(1,2,3,4)
    val f1 = (x:Int)=>println(x)
    //foreach遍历方法 参数需要一个函数对象
    //方式一
    a1.foreach(f1)
    //方式二
    println("=========================")
    a1.foreach((x)=>println(x))//函数参数的类型推导
    //方式三
    println("=========================")
    //对于只有一个参数的函数，省去参数外围的()
    a1.foreach(x=>println(x))
    println("=========================")
    //方式四
    //参数在=>右侧出现一次，可以使用_替换掉
    a1.foreach(println _)
    println("=========================")
    //更为简化的写法
    a1.foreach(println)
  }
}
