package objects

class Tiger(var color: String)

object Tiger {
  // apply返回伴生类对象
  def apply(color: String): Tiger = new Tiger(color)

  // unapply方法从对象中提取值
  def unapply(arg: Tiger): Option[String] = {
    if (arg == null) None
    else {
      Some(arg.color)
    }
  }

  def main(args: Array[String]): Unit = {
    val t1 = new Tiger("白色")

    val t2 = Tiger.apply("黑色") // 标准写法

    val t3 = Tiger("花色") // 简化写法

    println(t1)
    println(t2)
    println(t3)


    val option = Tiger.unapply(t2)
    println(option)

    val Tiger(color) = t2
    println(color)


  }
}
