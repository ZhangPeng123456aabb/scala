package chapter06
import scala.collection.mutable
object MapDemo01 {
  def main(args: Array[String]): Unit = {
    //方式1-构造不可变映射
    //1.默认Map是immutable.Map
    //2.key-value 类型支持Any
    //3.在Map的底层，每对key-value是Tuple2
    //4.从输出结果看到，输出顺序和声明顺序一致
    val map1 = Map("Alice"->10,"Bob"->20,"kotlin"->"北京")
    println("map1="+map1)

    //方式2-构造可变映射
    //1.从输出的结果可以看到，可变的map输出顺序和声名顺序不一致
    val map2 = mutable.Map("Alice"->10,"Bob"->20,"Kotlin"->"北京")
    println("map2="+map2)

    //方式3-创建空的映射
    val map3 = new mutable.HashMap[String,Int]
    println("map3="+map3)

    //方式4-对偶元组
    val map4 = mutable.Map(("Alice",10),("Bob",20),("Kotlin","北京"))
    println("map4="+map4)
  }
}
