package function

object Function02 {
  def main(args: Array[String]): Unit = {
    println(sum(1,2,3,4,5))
    println(sum(1))
    println(sum())
    (1 to  9).map("*" * _).foreach(println _)
  }
  def sum(nn:Int*):Int={
    var sum = 0
    for(n<- nn){
      sum += n
    }
    sum
  }
}
