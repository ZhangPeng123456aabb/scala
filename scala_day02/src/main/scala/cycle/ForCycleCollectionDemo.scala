package cycle

object ForCycleCollectionDemo {
  def main(args: Array[String]): Unit = {
    //list集合4个元素
//    val list = List("a","b","c","d")
//    for(n<- list){
//      println(n)
//    }
//list集合4个元素
    //==========倒序=========
//val list = List("a","b","c","d").reverse
//    for(n<- list){
//      println(n)
//    }
    for(n<- 1 to 10 by(2)){
      println(n)
    }
  }
}
