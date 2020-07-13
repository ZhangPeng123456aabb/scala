package traits

trait A {
  val name:String
}
trait B{
  this : A => //this:混入类型 =>[self type]在特质中混入特质，所依赖的成员可以在没有导入的情况下使用
  def sayHi = s"$name"
}
class C(val name:String) extends B with A{

}
object C{
  def main(args: Array[String]): Unit = {
    val c = new C("zs")
    println(c.sayHi)
  }
}
