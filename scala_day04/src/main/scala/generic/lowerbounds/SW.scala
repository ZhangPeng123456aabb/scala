package generic.lowerbounds

import generic.upperbounds.Dog

/**
  * 下边界 案例
  */
class SW
class Animals extends SW{
  var name:String=_
}
class Dogs extends Animals
class SmallDogs extends Dogs
class Keeper[T>:Dogs](x:T){
  def feed={
    println("feed food")
  }
}

/**
  * 下边界：包含父类  间接父类和本身
  */
object Keeper{
  def main(args: Array[String]): Unit = {
//    val s1 = new Keeper[Dogs](new Dogs)
//    s1.feed
//    val s2 = new Keeper[Animals](new Animals)
//    s2.feed
//    val s3 = new Keeper[SW](new SW)
//    s3.feed
//    val s4 = new Keeper[SmallDogs](new SmallDogs)
//    s4.feed
  }
}
