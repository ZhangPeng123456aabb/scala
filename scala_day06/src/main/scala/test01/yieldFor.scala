package test01

/**
  * @ProjectName Scala
  * @PackageName test01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/12 - 22:58
  */
object yieldFor {
  def main(args: Array[String]): Unit = {
    val res= for(i <- 1 to 10) yield{
      if(i % 2 ==0){
        i
      }else{
        "不是偶数"
      }
    }
    println(res)
  }
}
