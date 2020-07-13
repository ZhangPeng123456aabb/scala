package classes

import scala.beans.BeanProperty

object Fish {
  def main(args: Array[String]): Unit = {
    val fish = new Fish
    fish.kind="金枪鱼"
    println(fish.kind)
    fish.setKind("鲨鱼")
    println(fish.getKind)
  }
}
class Fish{
  //自动生成scala和java风格的getter和setter
  @BeanProperty var kind:String=""
}