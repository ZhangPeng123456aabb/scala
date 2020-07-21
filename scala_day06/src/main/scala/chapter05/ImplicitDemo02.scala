package chapter05

/**
  * @ProjectName Scala
  * @PackageName chapter05
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/21 - 9:48
  */
/**
  * 隐式转换丰富类库功能
  */
object ImplicitDemo02 {
  //编写一个隐式函数，丰富mySQL功能
  implicit  def addDelete(mysql:MySQL):DB={
    new DB
  }
  def main(args: Array[String]): Unit = {
    //创建mysql对象
    val mySQl = new MySQL
    mySQl.insert()
    mySQl.delete()
    mySQl.update()
  }
}
class MySQL{
  def insert():Unit={
    println("insert")
  }
}
class DB{
  def delete():Unit={
    println("delete")
  }
  def update():Unit={
    println("update")
  }
}
