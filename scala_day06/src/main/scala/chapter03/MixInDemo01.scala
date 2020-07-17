package chapter03

/**
  * @ProjectName Scala
  * @PackageName chapter03
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/16 - 15:25
  */
object MixInDemo01 {
  //动态混入
  def main(args: Array[String]): Unit = {
    //在不修改类的基础上，让他们可以使用trait方法
    val b = new OracleDB with Operator3
    b.insert(100)
    //混入多个特质
    val mySQL = new MySQL3 with Operator3 with Operator02
    mySQL.insert(100)
    println(mySQL.sum(1,2))
    //如有抽象方法，如何动态混入特质
    val mysql_ = new MySQL3_ with Operator3 {
      override def say(): Unit ={
          println("say")
      }
    }
    mysql_.insert(300)
    mysql_.say()
  }
}
trait Operator3{//特质
  def insert(id:Int):Unit={
    //方法实现
    println("插入数据 = "+id)
  }
}
trait Operator02{
  def sum(a:Int,b:Int):Int={
    a+b
  }
}
class OracleDB{
}
abstract class MySQL3{
}
abstract class MySQL3_{
  def say()
}
