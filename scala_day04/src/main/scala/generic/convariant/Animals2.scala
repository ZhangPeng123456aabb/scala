package generic.convariant

class Animals2
class Dog2 extends Animals2
class A2[-T]

object Dog2{
  def main(args: Array[String]): Unit = {
    //逆变
    val a = new A2[Animals2]()
    val a1:A2[Dog2] = a//父子关系 A[Animals] extends A[Dog]
  }
}
