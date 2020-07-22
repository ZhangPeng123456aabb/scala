package chapter06
import scala.collection.mutable
object SetDemo01 {
  def main(args: Array[String]): Unit = {
    val set = Set(1,2,3)//不可变
    println(set)

    val set2 = mutable.Set(1,2,"hello")//可以变
    //添加
    set2.add(3,4)
    set2.add(5)
    //删除
    set2.remove(1)
    set2 -= 2
    println("set2= "+set2)

    //比较set集合中的最大值
    println("最大值="+set.max)
    //比较set集合中的最小值
    println("最小值="+set.min)
  }
}
