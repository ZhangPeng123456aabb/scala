package practise

class FF {
//  private val ff = new FF{
//    var name:String=""
//    def sayHi:String={
//      s"Hello,$name"
//    }
//  }
}
object FF{
  def main(args: Array[String]): Unit = {
   val ff = new FF{
     var name:String=""
         def sayHi:String={
          s"Hello,$name"
         }
   }
    ff.name="zs"
    println(ff.sayHi)
  }
}