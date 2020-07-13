package cycle

import scala.util.control
import scala.util.control.Breaks

object BreakDemo {
  def main(args: Array[String]): Unit = {
    Breaks.breakable(
      for(n <- 1 to 10){
        //跳出循环
        if(n==5){
          Breaks.break
        }
        println(n)
      }
    )
  }
}
