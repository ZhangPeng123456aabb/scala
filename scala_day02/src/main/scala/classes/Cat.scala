package classes

object Cat {
  def main(args: Array[String]): Unit = {
    val c1 = new Cat("kitty",false,5,10,"加菲猫")
    val c2 = new Cat("kitty2",false,20,20,"野猫")
    println(c1.name+"\t"+c1.sex)
  }
}
class Cat(var name : String,var sex:Boolean,val age :Int,private var weight:Double,kind:String){
  //主构造器 参数列表 方法体整个类
  var color : String = ""
  println("主构造器开始")
  println("主构造器结束")
  def sayHi = "喵喵~~~~~~"+kind
}
