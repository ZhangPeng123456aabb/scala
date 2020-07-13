package practise

import scala.util.control.Breaks

object BreakDemo {
  def main(args: Array[String]): Unit = {
    var sum = 0
    Breaks.breakable(
      for(a<- 50 to 100){
        sum+=a
        if(a==88){
          Breaks.break()
        }
      }

    )
    println(sum)
  }
}
