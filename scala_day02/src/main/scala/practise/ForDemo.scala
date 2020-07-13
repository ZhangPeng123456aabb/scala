package practise

object ForDemo {
  def main(args: Array[String]): Unit = {
//    for(n<- 1 to 10){
//      println(n)
//    }
    for(n<- 1 to 9;m<- 1 to n){
      print(s"$n*$m="+(n*m)+"\t")
      if(n==m)println()
    }
  }
}
