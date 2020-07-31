package chapter14

object covariantcontravariant {
  def main(args: Array[String]): Unit = {
    val t1:Temp3[Sub] = new Temp3[Sub]("hello")
    val t2:Temp4[Super] = new Temp4[Sub]("hello")
    //val t3:Temp4[Sub] = new Temp4[Super]("hello")  //协变不支持
    val t3:Temp5[Sub] = new Temp5[Super]("hello")
    //val t3:Temp5[Super] = new Temp5[Sub]("hello") //逆变不支持
  }
}
//逆变
class Temp5[-A](title:String){
  //Temp3[+A]
  override def toString: String = {
    title
  }
}
//协变
class Temp4[+A](title:String){
  //Temp3[+A]
  override def toString: String = {
    title
  }
}
class Temp3[A](title:String){
  //Temp3[+A] Temp[-A]
  override def toString: String = {
    title
  }
}
//支持协变
class Super//父类

//Sub是Super的子类
class Sub extends Super