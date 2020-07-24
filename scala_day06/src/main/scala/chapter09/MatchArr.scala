package chapter09

import scala.collection.mutable.ArrayBuffer


object MatchArr {
  def main(args: Array[String]): Unit = {
//    val arrs = Array(Array(0),Array(1,0),Array(0,1,0),Array(1,1,0),Array(1,1,0,1))
//
//    for(arr <- arrs){
//      val result = arr match{
//        case Array(0) => "0"
//        case Array(x,y) => x + " = " + y
//        case Array(0,_*) => "以0开头和数组"
//        case _ => "什么集合都不是"
//      }
//      println("result = "+result)
//    }

    println("********************************************")
    val arrs2 = Array(Array(0),Array(1,0),Array(0,1,0),Array(1,1,0),Array(1,1,0,1))

    for(arr <- arrs2){
      val result = arr match{
        //case Array(0) => "0"
        case Array(x,y) => ArrayBuffer(y,x)
        //case Array(0,_*) => "以0开头和数组"
        case _ => "什么都不处理"
      }

      /**
        * 结果:
        * result = 什么都不处理
        * result = ArrayBuffer(0, 1)
        * result = 什么都不处理
        * result = 什么都不处理
        * result = 什么都不处理
        */
      println("result = "+result)
    }
  }
}
