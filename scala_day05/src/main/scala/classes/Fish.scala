package classes

import scala.beans.BeanProperty

class Fish {
  //自动生成scala和java风格的getter和setter
   @BeanProperty var kind:String =""
}
object Fish{
  def main(args: Array[String]): Unit = {
    val fish = new Fish
    fish.kind="大狗熊"
    println(fish.kind)

    val fish2 = new Fish
    fish2.setKind("鲫鱼")
    println(fish2.getKind)
  }
}