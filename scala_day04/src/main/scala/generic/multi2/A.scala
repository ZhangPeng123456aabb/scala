package generic.multi2

class A
class B extends A
class C
class D extends C
class E[T>:B with D](t:T)//T类型是B类型或C类型父类型
object E{
  def main(args: Array[String]): Unit = {
    val e1 = new E[A](new A)
    val e2 = new E[B](new B)
    val e3 = new E[C](new C)
    val e4 = new E[D](new D)
  }
}
