package generic.multi3

class A
class B extends A
class C extends B
class D[T>:C<:A](t:T)
object D{
  //承上启下
  def main(args: Array[String]): Unit = {
    val e1 = new D[B](new B)
    val e2 = new D[A](new A)
    val e3 = new D[C](new C)
    println(e1)
  }
}
