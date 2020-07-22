package chapter06
import scala.collection.mutable
object MapDemo01 {
  def main(args: Array[String]): Unit = {
    //方式1-构造不可变映射
    //1.默认Map是immutable.Map
    //2.key-value 类型支持Any
    //3.在Map的底层，每对key-value是Tuple2
    //4.从输出结果看到，输出顺序和声明顺序一致
    val map1 = Map("Alice"->10,"Bob"->20,"kotlin"->"北京")
    println("map1="+map1)

    //方式2-构造可变映射
    //1.从输出的结果可以看到，可变的map输出顺序和声名顺序不一致
    val map2 = mutable.Map("Alice"->10,"Bob"->20,"Kotlin"->"北京")
    println("map2="+map2)

    //方式3-创建空的映射
    val map3 = new mutable.HashMap[String,Int]
    println("map3="+map3)

    //方式4-对偶元组
    val map4 = mutable.Map(("Alice",10),("Bob",20),("Kotlin","北京"))
    println("map4="+map4)

    println("************* 判断key是否存在 *************")
    //方式1-使用map(key)
    println(map4("Alice"))
    //方式2-使用contains方法检查是否存在key
    if(map4.contains("Alice")){
      println("key存在,值="+map4("Alice"))
    }else{
      println("key不存在")
    }
    //方式3-使用map.get(key).get取值
    //1.如果key存在 map.get(key) 就会返回Some(值) ,然后Some(值),get就可以取出
    //2.如果key不存在 map.get(key) 就会返回None
    println(map4.get("Alice").get)
    //println(map4.get("Alice1").get)//抛异常 Exception in thread "main" java.util.NoSuchElementException: None.get

    //方式4-使用map4.getOrElse()取值
    println(map4.getOrElse("Alice~~","默认的值 魚 <.)))><<"))

    println("*******************map的添加和修改********************")
    val map5 = mutable.Map(("A",1),("B","北京"),("c",3))
    map5("A")=20
    map5("AA")=55
    println("map5="+map5)
    println("********************添加单个元素***********************")
    val map6 = mutable.Map(("A",1),("B","北京"),("C",3))
    map6 += ("D"-> 4)
    map6 += ("B" -> 50)
    println("map6="+map6)
    println("********************添加多个元素***********************")
    map6  += ("EE"->1,"FF"->3)
    println("map6="+map6)

    println("*********************删除元素**************************")
    map6 -= ("A","B","BB")
    println("map6="+map6)

    println("*********************map的遍历*************************")
    val map7 = mutable.Map(("A",1),("B","北京"),("C",3))
    for((k,v) <- map7)println(k+" is mapped to "+v)//A is mapped to 1  C is mapped to 3  B is mapped to 北京

    for(v <- map7.keys)println(v)//A  C  B
    for(v <- map7.values)println(v)//1  3  北京
    for(v <- map7)println(v)// (A,1) (C,3)  (B,北京)
  }
}
