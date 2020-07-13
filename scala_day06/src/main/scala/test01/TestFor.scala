package test01

/**
  * @ProjectName Scala
  * @PackageName test01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/12 - 20:50
  */
object TestFor {
  def main(args: Array[String]): Unit = {
//    val start = 1
//    val end = 10
//    for(i <- start to end){
//      println("你好"+i)
//    }


//    for(i<-1 to 3 if i!=2){
//      print(i + " ")
//    }

    for(i <- 1 to 3; j <- 1 to 3 ){
      println(" i=" + i + " j="+ j)
    }
  }
}
