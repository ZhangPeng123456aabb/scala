package test01

/**
  * @ProjectName Scala
  * @PackageName test01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/12 - 23:09
  */
object stepfor {
  def main(args: Array[String]): Unit = {
    for(i <- Range(1,10,2)){
      println("i= "+i)
    }
    println("*********************")
    for(i <- 1 to 10 if i%2==1){
     println("i=" +i)
    }
    println("**********************")
    val num = 6
    for(i <- 0 to num){
      printf("%d + %d = %d\n",i,(num-i),num)
    }
  }
}
