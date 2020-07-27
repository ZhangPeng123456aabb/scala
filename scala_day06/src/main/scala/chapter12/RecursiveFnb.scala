package chapter12
object RecursiveFnb {
  def main(args: Array[String]): Unit = {
    var count = BigInt(0)
    //1 2 3 4 5 ?
    println(fnb(50))
    println("递归的次数是= "+count)
    /**
      *研究下递归求斐波那契的数的递归次数的增长情况
      *递归的次数呈指数增长
      * @param n
      * @return
      */
    def fnb(n:BigInt):BigInt = {
      count += 1
      if(n==1 || n==2) 1
      else fnb(n-1) + fnb(n-2)
    }
  }
}
