package inherit

abstract class Flower {
  var id:Int=0
  //抽象字段[只有声明，没有实现]
  var name:String
  //抽象方法
  def open : String
  def grow():Unit={
    println("茁壮成长!!")
  }

}
class RoseFlower extends Flower{
  //必须实现抽象字段和抽象方法，其实override可以省略
  override var name: String = "玫瑰花"
  override def open: String = "玫瑰花五颜六色，啊！这是爱的味道......"
}
object RoseFlower{
  def main(args: Array[String]): Unit = {
    val flower:RoseFlower = new RoseFlower
    println(flower.id)
    println(flower.name)
    println(flower.open)

    val flower2 = new Flower {
      override var name: String = "菊花"

      override def open: String = "菊花可以入药"
    }
    println(flower2.name)
    println(flower2.open)
  }
}