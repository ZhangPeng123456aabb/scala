package chapter08

object IteratorDemo01 {
  def main(args: Array[String]): Unit = {
    val iterator = List(1,2,3,4,5).iterator//得到迭代器
    println("---------------遍历方式一--------------")
//    while(iterator.hasNext){
//      println(iterator.next())
//    }
    println("---------------遍历方式二 for--------------")
    for(enum <- iterator){
      println(enum)
    }
  }
}
