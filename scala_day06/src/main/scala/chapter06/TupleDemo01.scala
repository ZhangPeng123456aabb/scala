package chapter06

/**
  * @ProjectName Scala
  * @PackageName chapter06
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/21 - 17:45
  */
object TupleDemo01 {
  def main(args: Array[String]): Unit = {
    //创建
    //说明1.tuple1就是一个类型是Tuple5
    //简单说明:为了高效的操作元组，编译器根据元素的个数不同，对应不同的元组类型
    //分别Tuple1------Tuple22
    val tuple1 = (1,2,3,"hello",4)
    println(tuple1)


    //访问元组
    val t1 = (1,"a","b",true,2)
    println(t1._1)//访问元组的第一个元素
    println(t1.productElement(0))
  }
}
