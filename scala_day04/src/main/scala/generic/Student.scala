package generic

class Student[T,K](var name:T,var sex:K){
  //泛型类
  override def toString: String = name+"\t"+sex
}
object Student {
  def main(args: Array[String]): Unit = {
    //在创建对象时 指定泛型信息
    val s1 = new Student[String,Boolean]("zs",false)
    println(s1)
    val s2 = new Student("zs",false)
    //类型推导 根据输入值  自动推断泛型
    println(s2)
    Array(1,2,3,4)
  }
}
