package array

object BubbleSort {
  def main(args:Array[String]): Unit ={
    //冒泡排序
    val a1=Array(3,1,5,4,10,0,100,45)
    for(m<- 0 until (a1.length)){
      for(n<- 0 until (a1.length-m-1)){
        if(a1(n)>a1(n+1)){
          var tmp = a1(n)
          a1(n)=a1(n+1)
          a1(n+1)=tmp
        }
      }
    }
    for(b<-a1){
      println(b)
    }
  }
}
