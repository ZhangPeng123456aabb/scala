package collection

object SetTest {
  def main(args: Array[String]): Unit = {
    //set不可变和可变
    val s1 = Set(1,2,3)
    val s2 = scala.collection.mutable.Set(2,3,4)
    val s3 = s1+4
    s2+=4
//    println(s1 - 1)
//    println(s2 -=3)
//    println(s1.++(s2))

    println(s1 & s2)
    println(s1 | s2)

    println(s1.mkString(","))
    println(s2.mkString(","))
    println(s3.mkString(","))
  }
}
