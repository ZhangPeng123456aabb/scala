package array

object SelectSort {
  def main(args: Array[String]): Unit = {
    val a1 = Array(45,2,45,99,100,12,66,55)
    for(m<- 0 until (a1.length-1)){
      for(n<- (m+1) until (a1.length)){
        if(a1(m)>a1(n)){
          var tmp=a1(m)
          a1(m)=a1(n)
          a1(n)=tmp
        }
      }
    }
    for(a<-a1){
      println(a)
    }
  }
}
