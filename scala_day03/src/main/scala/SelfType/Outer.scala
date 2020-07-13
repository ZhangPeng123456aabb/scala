package SelfType

class Outer {
  outer =>
  val v1 = "here"
  class Inner{
    inner =>
    val v1 = "--------------"
    println(outer.v1)//用outer 表示外部类，相当于Outer.this
    println(inner.v1)
  }
}
