package array

object Array05 {
  def main(args: Array[String]): Unit = {
    val a1 = Array(1,2,3,4,5,-5,-4,-3,-2,-1)
    var first = true
    var position = 0
    for(n <- a1.indices){
      if(first || a1(n)>=0){
        if(a1(n)<0){
          first=false
          position=n
        }
      }
    }
    println(position)
    val ab = a1.toBuffer
    ab.trimEnd(a1.length-position-1)
    println(ab)
  }
}
