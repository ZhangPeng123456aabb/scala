package chapter09

object MatchForDemo {
  def main(args: Array[String]): Unit = {
    val map = Map("A"->1,"B"->0,"c"->3)
    for((k,v) <- map){
      println(k + "->" + v) //结果:A->1 B->0 c->3
    }
    println("**************************")
    for((k,0) <- map){
      println(k + "-->" +0) //结果: B --> 0
    }
    println("**************************")
    for((k,v) <- map if v>=1){
      println(k + "--->" +v)//结果:A--->1 c--->3
    }
  }
}
