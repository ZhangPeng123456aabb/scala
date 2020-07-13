package HighFunctions

object Fumction04 {
  def main(args: Array[String]): Unit = {
//    Array(1,2,3,4,5,6,7,8,9,10).map("*" *_).foreach(println)
//    (1 to 10).filter(_%2==0).foreach(println)
    //(1 to 10).filter(_ % 2 == 0).reduceLeft((x,y) => x+y)
    val a1 = Array("Hello Scala","Hello Hadoop","Hello Hello Hello")
    a1.flatMap(_.split(" ")).map((_,1)).groupBy(_._1).map(x=>(x._1,x._2.length)).foreach(println)
  }
}
