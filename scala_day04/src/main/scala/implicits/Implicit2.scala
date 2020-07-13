package implicits

object Implicit2 {
  def main(args: Array[String]): Unit = {
    val man2 = new Man2("zs")
    man2.fly
    println(man2.name)
    implicit class SuperMan2(man2:Man2){
      var name:String=_
      def fly={
        println("飞翔........")
      }
    }
  }
}
class Man2(var name: String)
//implicit class SuperMan2(man2:implicits.Man2){
//  var name:String=_
//  def fly={
//    println("飞翔........")
//  }
//}
//object DD{
//  implicit class SuperMan2(man2:implicits.Man2){
//    var name:String=_
//    def fly={
//      println("飞翔........")
//    }
//  }
//}