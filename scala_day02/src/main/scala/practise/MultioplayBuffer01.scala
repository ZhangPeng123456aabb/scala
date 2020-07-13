package practise

object MultioplayBuffer01 {
  def main(args: Array[String]): Unit = {
    //val value = Array.ofDim(Int)(3,4)
    val arr = Array.ofDim[Int](3,4)
   // arr=Array[Byte]()
    for(m<- arr){
      println(m.mkString(","))
      println()
    }
    println("=========================")
    for(m<- arr){
      println(m.mkString(","))
      println()
    }
  }
}
