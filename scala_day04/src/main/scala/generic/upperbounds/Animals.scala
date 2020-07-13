package generic.upperbounds

/**
  * 上边界 案例
  */
class Animals {
  var name:String="动物"
}
class Dog extends Animals
class Keeper[T<: Animals](x:T){
  //有参主构造器 接受一个T类型 必须Animals的子类型
  def feed={
    println(x.name)
  }
}
object Keeper{
  def main(args: Array[String]): Unit = {
//    val k1 = new Keeper[Dog](new Dog)//上边界 父子ok
//    k1.feed

    val k2 = new Keeper[Animals](new Animals)
    //上边界 自己=自己 ok
    k2.feed

  }
}