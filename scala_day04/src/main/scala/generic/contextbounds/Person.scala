package generic.contextbounds
class Person(var name:String,var age:Int)
object Person {
//声明一个隐式值
  implicit val ordering:Ordering[Person]=new Ordering[Person](){
    /**
      * 比较大小的方法
      * x
      * y
      * 1 大于  -1 小于
      */
    override def compare(x: Person, y: Person): Int = {
      if(x.age>y.age) 1
      else -1
    }
  }
def main(args: Array[String]): Unit = {
  val p1 = new Person("zs",10)
  val p2 = new Person("ls",100)
  val tools = new CompareUtils[Person](p1,p2)
  if(tools.comparable()==1){
    println("大于")
  }else{
    println("小于")
  }
}
}
class CompareUtils[T:Ordering](p1:T,p2:T){
  def comparable()={
    //获取隐式值
    val tools = implicitly[Ordering[T]]
    tools.compare(p1,p2)
  }
}