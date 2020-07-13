package collection

object MapTest {
  def main(args: Array[String]): Unit = {
    //声明 方式一
    val m1 = Map("bj"->"北京","tj"->"天津","sh"->"上海")//不可变的map
    val m2 = scala.collection.mutable.Map("bj"->"北京","tj"->"天津","sh"->"上海")//可变的map
    //声明 方式二
    val m3 = Map(("tj","天津"),("bj","北京"))
    val m4 = scala.collection.mutable.Map(("bj","北京"),("tj","天津"))
    //操作
    //println(m1("bj"))
    println(m1+(("gz","广州")))//不可变得map集合 返回新的map集合
    m2("bj")="bjj"//可变的map集合
    m2("sz")="深圳"
    m2+=(("zz","郑州"))
    m2 -=("tj")//tj的这对kv移除
    //可排序的map集合TreeMap
    val sm = scala.collection.SortedMap[String,String]("bj"->"北京","tj"->"天津","sh"->"上海","aq"->"安庆")
    println(sm)

    val iterator = m1.keySet.toIterator
    while(iterator.hasNext){
      val key = iterator.next()
      val value = m1(key)
      println(s"$key  $value")
    }
    println("==========================")
    m1.foreach(println)
    println("==========================")
    m1.foreach(t =>println(t._1+"\t"+t._2))
    println("==========================")
    m2.foreach(t =>println(t._1+"\t"+t._2))
  }
}
