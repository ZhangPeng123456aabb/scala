package patternmatch

/**
  * 模式匹配中的 集合匹配
  */
object Pattern5 {
  def main(args: Array[String]): Unit = {
//    //val list = List(1,2,3)
//    val list=List(1,1,2,3)
//    //匹配集合元素数量
//    list match{
//      case List(e1) =>println("匹配1个元素")
//      case List(e1,e2)=>println("匹配2个元素")
//      case List(e1,e2,e3) =>println("匹配3个元素")
//      case List(e1,e2,e3,e4) =>println("匹配4个元素")
//      case List(_*) =>println("匹配任意多个元素")//default
//    }
//    //匹配集合元素内容
//    list match{
//      case List(1) =>println("匹配1个元素")
//      case List(1,2) =>println("匹配2个元素")
//      case List(1,2,3) =>println("匹配3个元素")
//      case List(1,2,3,4) =>println("匹配4个元素")
//      case List(1,_*)=>println("匹配以1开头的任意集合")
//    }
    println("==================================")
    //匹配类型 支持Array 不支持集合的泛型匹配List、Set、Map
//    val a1:Any=Array[Int](1,2,3)
//    val a2:Any=Array[String]("a","b","c")
//
//    a1 match{
//      case x:Array[Int]=>println("array[Int]")
//      case y:Array[String]=>println("array[String]")
//      case _=>println("array[other]")
//    }
  println("==================================")
    // 匹配类型 支持Array支持   不支持集合的泛型匹配 List、Set、Map
//    val l1: Any = List[Int](1, 2, 3)
//    val l2: Any = List[String]("a", "b", "c")
//    12 match {
//      case z: Array[String] => println("array[String]")
//      case y: List[String] => println("list[String]")
//      case x: List[Int] => println("list[Int]") // list[Int]
//      case _ => println("list[other]")
//    }
    val t3:Any=(1,"zs",false,10.0)
    t3 match{
      case (n1,n2,n3)=>println(n1,n2,n3)//匹配元组内容数量
      case t:Tuple3[Int,String,Boolean]=>println(t)//匹配元组类型
      case (1,_x:String,_y:Boolean,_z:Double)=>println(_x+"\t"+_y+"\t"+_z)//匹配元组值和类型
      case t2:(Int,String,Boolean,Double)=>println(t2)//匹配元组类型
    }
  }
}
