package generic.convariant

class Animals
class Dog extends Animals
class A[+T]

object Dog{
  def main(args: Array[String]): Unit = {
    //协变
    val a = new A[Dog]()
    val a2:A[Animals] = a//父子关系  A[Dog] extends A[Animals]

  }
}
