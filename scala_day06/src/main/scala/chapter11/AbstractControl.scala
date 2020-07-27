package chapter11

object AbstractControl {
  def main(args: Array[String]): Unit = {
    /**
      *myRunThread就是一个抽象控制
      * 是没有输入，也没有输出的函数f1:() => Unit
      * @param f1
      */
    def myRunInThread(f1: =>Unit)={
      new Thread{
        override def run(): Unit = {
          f1
        }
      }.start()
    }
//    myRunInThread{
//      () =>
//        println("干活罗！5秒完成...")
//        Thread.sleep(5000)
//        println("干完！")
//    }
    //对于没有输入，也没有返回值函数，可以简写成如下形式
    myRunInThread{
      println("干活罗！5秒完成...")
      Thread.sleep(5000)
      println("干完！")
    }
  }
}
