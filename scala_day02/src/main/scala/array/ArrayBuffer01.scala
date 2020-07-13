package array

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks

object ArrayBuffer01 {
  def main(args: Array[String]): Unit = {
//    val ab1 = ArrayBuffer[Int]()
//    val ab2 = ArrayBuffer[Int](10,20,30)
//    ab2 += 40
//    ab2.+=(50)
//    ab2.+=(60,70,80)
//    ab2 ++= ArrayBuffer(90,100)
//    ab2.trimEnd(3)
//    ab2.trimStart(2)
//    ab2.insert(2,45,46,47,48,49)
//    println(ab2)
val a1 = Array(1,2,3,4,5,-6,5,-5,-4,-3)
    val a2 = ArrayBuffer[Int]()
    var n = 0
    Breaks.breakable(
      for(a3<-a1 if a3<0){
        n=a3
        Breaks.break()
      }
    )
    for(a<-a1 if a>0 || a==n){
      a2+=a
    }
    for(a4<-a2){
      println(a4)
    }
  }
}
