package chapter11

object PartialFuncDemo1 {
  def main(args: Array[String]): Unit = {
    //使用偏函数解决
    val list = List(1,2,3,4,"hello")
    /**
      *定义一个偏函数
      *1.PartialFunction[Any,Int]表示偏函数接收的参数类型是Any,返回类型是Int
      *2.isDefinedAt(x:Any)如果返回true,就回去调用apply,构建对象实例，如果false，过滤
      *3.apply构造器，对传入的值+1,并返回(新的集合)
      */
    val partialFun = new PartialFunction[Any,Int] {
      override def isDefinedAt(x: Any): Boolean = {
        println("x= "+x)
        x.isInstanceOf[Int]
      }

      override def apply(v1: Any): Int = {
        println("v1= "+v1)
        v1.asInstanceOf[Int]+1
      }
    }

    /**
      * 使用偏函数
      * 说明：如果是使用偏函数，则不能使用map,应该使用collect
      * 说明一下偏函数的执行流程
      * 1.遍历list所有元素
      * 2.然后调用val element = if(partialFun-isDefineAt(list单个元素)){partialFun-apply(list单个元素)}
      * 3.每得到一个element,放入到新的集合中，然后返回
      */
    val list2 = list.collect(partialFun)
    println("list2= "+list2)
  }
}
