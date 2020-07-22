package chapter06

object ListDemo01 {
  def main(args: Array[String]): Unit = {
    //创建时直接分配元素
    /**
      * 在scala中list是不可变的，如果使用可变的，则使用ListBuffer
      *
      */
    val list01 = List(1,2,3,"Hello")
    println(list01)

    //创建空集合
    val list02 = Nil
    println(list02)

    //访问list元素
    //1是索引，表示取出第二个元素
    val value1 = list01(1)
    println("value1="+value1)

    println("************list追加元素后的结果*****************")
    val list1 = List(1,2,3,"abc")
    //通过:+给list追加数据
    val list2 = list1 :+ 4 //(1,2,3,"abc",4)
    println(list1)
    println(list2)

    println("************** :: ****************")
    //::符号的使用
    val list4 = List(1,2,3,"abc")
    val list5 = 4 :: 5 :: 6 :: list4 :: Nil
    println(list5)

    println("*********** ::: ***************")
    val list6 = 4 :: 5 :: 6 :: list5 ::: list5
    println(list6)

  }
}
