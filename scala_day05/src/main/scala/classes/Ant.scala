package classes
//scala默认提供一个无参的主构造器
class Ant {
  var kind:String = ""
  var sex:Boolean=false
  //辅助构造器
  def this(kind:String){
    //调用主构造器
    this()
    this.kind=kind
  }
  //辅助构造器 2
  def this(kind:String,sex:Boolean){
    this(kind)
    this.sex=sex
  }
}
object Ant{
  def main(args: Array[String]): Unit = {
    val a1 = new Ant
    val a2 = new Ant("白蚂蚁")
    val a3 = new Ant("黑蚂蚁",true)
    println(a1)
    println(a2.kind)
    println(a3.kind)
  }
}
