package chapter08

import scala.collection.mutable.ArrayBuffer

object Exercise02 {
  def main(args: Array[String]): Unit = {
    val sentence = "AAAAAAAAAAABBBBBBBBBBCCCCCCDDDDDDDDDDD"
    val arrayBuffer = new ArrayBuffer[Char]()

    sentence.foldLeft(arrayBuffer)(putArray)
    println("arrayBuffer= "+arrayBuffer)
  }
  def putArray(arr:ArrayBuffer[Char],c:Char):ArrayBuffer[Char]={
    //将c放入到arr中
    arr.append(c)
    arr
  }
}
