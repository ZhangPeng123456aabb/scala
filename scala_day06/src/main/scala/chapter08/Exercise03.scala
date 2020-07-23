package chapter08

import scala.collection.mutable
object Exercise03 {
  def main(args: Array[String]): Unit = {
    val sentence = "AAAAAAAAAABBBBBBBBCCCCCDDDDDDD"
    val charToInt = sentence.foldLeft(Map[Char,Int]())(charCount)
    println("charInt= "+charToInt)

    //使用可变的map,效率更高
    //1.先创建一个可变的map,作为左折叠的第一个参数
    val map3 = mutable.Map[Char,Int]()
    val map = sentence.foldLeft(map3)(charCount1)
    println("map= "+map)
  }
  //使用的是不可变map实现
  def charCount(map:Map[Char,Int],char:Char):Map[Char,Int]={
    map + (char -> (map.getOrElse(char,0)+1))
  }

  //使用的是可变map实现
  def charCount1(map:mutable.Map[Char,Int],char:Char):mutable.Map[Char,Int]={
    map + (char -> (map.getOrElse(char,0)+1))
  }
}
