package array

object Array03 {
  def main(args: Array[String]): Unit = {
    val a1 = Array(1,2,3,4,5,-5,-4,-3)
    var flag = 1
    var index = 1
    a1.map{e=>
      if(flag>0){
        if(e>0){
          index=index + 1
        }
        if(e<0)
          flag = -1
      }
    }
    var i = 0
    a1.filter{
      e=>
        i=i+1
        e>=0 || i==index
    }.foreach(println)
  }
}
