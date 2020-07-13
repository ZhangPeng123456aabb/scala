package objects

object IdFactory {
  private var id:Int=0
  def increment:Int={
    id+=1
    id
  }
  def main(args: Array[String]): Unit = {
    println(IdFactory.increment)
    println(IdFactory.increment)
    println(IdFactory)
    println(IdFactory)
  }
}
