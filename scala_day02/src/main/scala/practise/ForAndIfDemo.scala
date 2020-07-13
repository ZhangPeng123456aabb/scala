package practise

object ForAndIfDemo {
  def main(args: Array[String]): Unit = {
    //守卫
    for(a<- 1 to 10 if a%2==0 if a>5 if a!=10){
      println(a)
    }
  }
}
