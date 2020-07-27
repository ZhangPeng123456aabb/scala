package chapter12

object RecursiveMaxList {
  def main(args: Array[String]): Unit = {
    println(MyMax(List(1,-1,999,9,100)))
  }

  /**
    * 使用递归求List中的最大元素
    */
  def MyMax(xs:List[Int]):Int = {
    //如果为empty,抛出异常
    if(xs.isEmpty)
      throw new java.util.NoSuchElementException
    if(xs.size == 1)//如果有一个元素，就是它
      xs.head
    //递归告诉计算机做什么，而不是告诉计算机怎么做(迭代)
    else if (xs.head > MyMax(xs.tail)) xs.head else MyMax(xs.tail)
  }
}
