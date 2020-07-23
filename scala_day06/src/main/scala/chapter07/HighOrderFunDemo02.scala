package chapter07

object HighOrderFunDemo02 {
  def main(args: Array[String]): Unit = {
    test2(sayOK)
  }
  //说明test2是一个高阶函数，可以接受一个没有输入，返回unit的函数
  def test2(f:()=>Unit): Unit ={
    f()
  }
  def sayOK()={
    println("sayOKKK...")
  }
  def sub(n1:Int): Unit ={

  }
}
