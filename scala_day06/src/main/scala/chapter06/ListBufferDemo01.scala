package chapter06
import scala.collection.mutable.ListBuffer
object ListBufferDemo01 {
  def main(args: Array[String]): Unit = {
    //创建ListBuffer
    val lst0 = ListBuffer[Int](1,2,3)

    //如何访问
    println("lst0(2)="+lst0(2))//lst0(2)=3
    for(item <- lst0){//遍历，有序的
      println("Item="+item)
    }

    val lst1 = new ListBuffer[Int]//空的LIstBuffer
    lst1 += 4 //lst1=4
    lst1.append(5)

    lst0 ++=lst1
    println(lst0)//有序添加
    val lst2 = lst0 ++ lst1
    val lst3 = lst0 :+ 5

    println("=========删除==========")
    println("lst="+lst1)
    lst1.remove(1)
    for(item <- lst1){
      println(item)
    }
  }
}
