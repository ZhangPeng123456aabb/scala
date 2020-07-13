package generic.multi

trait A
trait B
class C extends B with A //动态混入
//多重  上边界   T类型必须同时是A和B类型的字类型【并且】
class E[T<: A with B](t:T)

object E{
  def main(args: Array[String]): Unit = {
    val e1 = new E[C](new C)
    println(e1)
  }
}
