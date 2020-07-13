package practise

object ForCycleCollectionDemo {
  def main(args: Array[String]): Unit = {
    val result = Array(1,2,3,4,5,6,7,8,9).reverse
    for(a<-result){
      println(a)
    }
  }
}
