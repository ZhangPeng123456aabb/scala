package chapter10

/**
  * 给你一个集合 val list=List(1,2,3,4,"abc"),请完成如下要求:
  *1.将集合list中的所有数字+1,并返回一个新的集合
  *2.要求忽略掉非数字的元素，即返回新的集合形式为(2,3,4,5)
  */
object PartialFunDemo01 {
  def main(args: Array[String]): Unit = {
    /**
      * 思路一 filter + map 方式解决
      * 虽然可以解决问题，但是麻烦
      */
    val list = List(1,2,3,4,"hello")
    //先过滤，再map
    println(list.filter(f1).map(f3).map(f2))//List(2, 3, 4, 5)

    //思路二 -模式匹配 虽然很接近，但不够完美
    val list1 = list.map(addOne2)
    println("list1= "+list1) //list1= List(2, 3, 4, 5, ())
  }
  def f1(n:Any):Boolean={
    n.isInstanceOf[Int]
  }
  def f2(n:Int):Int={
    n + 1
  }
  def f3(n:Any):Int={
    n.asInstanceOf[Int]
  }

  def addOne2(i : Any): Any = {
    i match {
      case x:Int => x+1
      case _ =>
    }
  }
}
