package test01

/**
  * @ProjectName Scala
  * @PackageName test01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/13 - 10:58
  */
object multi {
  def main(args: Array[String]): Unit = {
    val num = 9
    for(i <- 1 to num){
      for(j <- 1 to i){
        printf("%d * %d = %d\t",j,i,j*i)
      }
      println()
    }
  }
}
