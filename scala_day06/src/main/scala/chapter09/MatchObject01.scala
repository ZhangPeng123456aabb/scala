package chapter09

object MatchObject01 {
  def main(args: Array[String]): Unit = {
    val names:String = "Alice,Bob,Thomas"
    names match{
      /**
        * 当执行 case Names(first,second,third)
        * 1.会调用unapplySeq(Str),把"Alice,Bob,Thomas"传入给str
        * 2.如果返回的是Some("Alice","Bob","Thomas"),分别给(first,second,third)
        *   注意，这里的返回值的个数需要和(first,second,third)要一样
        * 3.如果返回的None,表示匹配失败
        */
      case Names(first,second,third)=>{
       println("the string contains three people's names")
        //打印字符串
        println(s"$first $second $third")
      }
      case _ => println("nothing matched")
    }
  }
}
object Names{
  //当构造器是多个参数时，就会触发这个对象提取器
  def unapplySeq(str:String): Option[Seq[String]] = {
    if(str.contains(",")) Some(str.split(","))
    else None
  }
}
/**
  * 代码小结:
  * 1.当case后面的对象提取器方法的参数为多个，则会默认调用def unapplySeq()方法
  * 2.如果unapplySeq返回是Some,获取其中的值，判断得到的sequence中的元素的个数是否是三个，
  * 如果是三个，则把三个元素分别取出，赋值给first,second和third
  * 3,其他规则不变
  */