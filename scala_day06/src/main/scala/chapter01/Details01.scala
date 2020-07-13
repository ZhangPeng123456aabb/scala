package chapter01

/**
  * @ProjectName Scala
  * @PackageName chapter01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/13 - 15:16
  */
object Details01 {
  def main(args: Array[String]): Unit = {
    println(sayOk("Marry"))
  }
  //name形参的默认值jack
  def sayOk(name:String="jack")={
    name + " ok! "
  }
}
