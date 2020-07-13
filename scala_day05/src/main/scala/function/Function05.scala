package function

object Function05 {
  def main(args: Array[String]): Unit = {
    //匿名函数：没有函数名
    val f1:Function2[Int,Int,Int]=(x:Int,y:Int)=>x+y
    println(f1)

    val f2=()=>"Hello:"
    println(f2)

    val f3 = (x:Int,y:Int,z:Int)=>{
      x*y*z
    }
    println(f3)
    List("a","b").foreach(print)

    println(f1(1,2))

    println(f2())

    println(f2())

    println(f3(2,2,2))
  }
}
