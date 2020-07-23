package chapter08

object ParDemo01 {
  def main(args: Array[String]): Unit = {
    (1 to 5).foreach(println(_))
    println()
    //输出的结果是无序的，说明是将println任务分配给不同的cpu
    (1 to 5).par.foreach(println(_))
  }
}
