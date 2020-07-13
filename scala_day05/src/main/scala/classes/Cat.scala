package classes

class Cat(var name:String,var sex:Boolean,val age:Int,private var weight:Double,kind :String) {
  var color :String = ""
  println("主构造器开始")
  println("主构造器结束")
  def sayHi="喵喵~~~~~,品种: "+kind
}
object Cat{
  def main(args: Array[String]): Unit = {
    val c1 = new Cat("kitty",false,5,10,"加菲")
    val c2 = new Cat("kitty2",true,5,10,"野猫")
    println(c2.name+"\t"+c2.sex)
  }
}
