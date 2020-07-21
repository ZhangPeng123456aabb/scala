package chapter06

/**
  * @ProjectName Scala
  * @PackageName chapter06
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/21 - 16:09
  */
object MultiplyArray {
  def main(args: Array[String]): Unit = {
    //创建
    val arr = Array.ofDim[Int](3,4)

    //遍历
    for(item <- arr){
      for(item2 <- item){
        print(item2+"\t")
      }
      println()
    }
    //指定取出
    println(arr(1)(1))
    arr(1)(1)=99
    println(arr(1)(1))

    //使用传统的下标的方式来进行遍历
    println("========================")
    for(i<- 0 to arr.length-1){
      for(j <- 0 to arr(i).length-1){
        printf("arr[%d][%d]=%d\t",i,j,arr(i)(j))
      }
      println()
    }
  }
}
