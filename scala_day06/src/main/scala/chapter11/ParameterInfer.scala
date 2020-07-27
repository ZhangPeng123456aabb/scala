package chapter11

object ParameterInfer {
  def main(args: Array[String]): Unit = {

    val list = List(1,2,3,4)
    println(list.map((x:Int)=>x+1))//List(2,3,4,5)
    println(list.map((x)=>x+1))//List(2,3,4,5)
    println(list.map(x=>x+1))//List(2,3,4,5)
    println(list.map(_+1))//List(2,3,4,5)

    println(list.reduce((n1:Int,n2:Int)=>n1+n2))
    println(list.reduce((n1,n2)=>n1+n2))
    val res = list.reduce(_+_)
    println("res= "+res)
  }
}
