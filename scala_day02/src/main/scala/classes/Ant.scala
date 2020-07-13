package classes

object Ant {
  def main(args: Array[String]): Unit = {
    val a1 = new Ant
    val a2 = new Ant("白孔雀")
    val a3 = new Ant("黑孔雀",true)
    println(a1.kind)
    println(a2.kind)
    println(a3.kind,a3.sex)
  }
}
class Ant{
  var kind:String=""
  var sex:Boolean=false
  //辅助构造器
  def this(kind:String){
    this()
    this.kind=kind
  }
  //辅助构造器二
  def this(kind:String,sex:Boolean){
    this(kind)
    this.sex=sex
  }
}