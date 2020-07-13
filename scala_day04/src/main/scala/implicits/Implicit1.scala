package implicits

object Implicit1 {
  def main(args: Array[String]): Unit = {
    val man = new Man("隔壁小刘")
    println(man.fly)//语法支持 通过隐士转换增强原始类的功能
    println(man.fly)
  }
  implicit def manToSuperMan(man:Man):SuperMan=new SuperMan(man.name)
}
class Man(var name:String){
  println("init man")
}
class SuperMan(var name:String){
  println("init superman")
  def fly():String={
    "快乐飞翔.........."
  }
}
