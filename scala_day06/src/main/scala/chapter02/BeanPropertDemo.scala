package chapter02

import scala.beans.BeanProperty

/**
  * @ProjectName Scala
  * @PackageName chapter02
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/14 - 11:27
  */
object BeanPropertDemo {
  def main(args: Array[String]): Unit = {
    val car = new Car
    car.name="宝马"
    println(car.name)

    car.setName("奔驰")
    println(car.getName)
  }
}

class Car{
  @BeanProperty var name:String = _
}
