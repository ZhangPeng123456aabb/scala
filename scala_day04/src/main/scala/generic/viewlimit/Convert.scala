package generic.viewlimit

import java.text.SimpleDateFormat
import java.util.Date

class Convert[T <% Date](val first:T) {
    def print()={
      println(first.toLocaleString)
    }
}
object Convert{
  //定义隐式转换函数
  implicit def str2Date(str:String):Date={
    new SimpleDateFormat("yyyy-MM-dd").parse(str)
  }

  def main(args: Array[String]): Unit = {
    val c1 = new Convert[String]("2019-10-25")
    c1.print()
  }
}
