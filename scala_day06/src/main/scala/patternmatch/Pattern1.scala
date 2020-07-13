package patternmatch

/**
  * 默认匹配中的值匹配，不需要break结束case分支
  */
object Pattern1 {
  def main(args: Array[String]): Unit = {
    val city = readLine("city: ")
    city match {
      case "bj" =>println("北京")
      case "sh" =>println("上海")
      case _=>println("other")//类似于java的default
    }
  }
}
