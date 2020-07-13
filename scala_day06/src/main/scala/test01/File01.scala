package test01

import scala.io.Source

object File01 {
  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("I:\\中文\\test1.txt","UTF-8")
    val lineIterator = source.getLines()
    //lineIterator.foreach(println)
    //source.getLines.toArray.foreach(println)
//    val contents = source.mkString
//    println(contents)
    //source.foreach(println)
    source.buffered.foreach(println)
    source.close()
  }
}
