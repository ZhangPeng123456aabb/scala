package chapter15

import scala.collection.mutable

object SparseArr {
  def main(args: Array[String]): Unit = {
    //演示一个稀疏数组的使用
    val chessMap = Array.ofDim[Int](11,11)
    //初始化地图
    chessMap(1)(2) = 1 //1 表述黑子
    chessMap(2)(3) = 2 //2表示白子

    //输出原始的地图
    for(item <- chessMap){
      for(item2 <- item){
        printf("%d\t",item2)
      }
      println()
    }
    val arrayBuffer = mutable.ArrayBuffer[Node]()
    for(i <- 0 until chessMap.length){
      for(j <- 0 until chessMap(i).length){
        if(chessMap(i)(j) != 0){
          val node = new Node(i,j,chessMap(i)(j))
          arrayBuffer.append(node)
        }
      }
    }
    println("*************遍历后的稀疏数组**************")
    for(i <- arrayBuffer){
      printf("%d\t%d\t%d\n",i.row,i.cow,i.value)
    }
  }
}
class Node(val row:Int,val cow:Int,val value:Int)