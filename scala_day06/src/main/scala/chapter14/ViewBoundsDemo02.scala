package chapter14

object ViewBoundsDemo02 {
  def main(args: Array[String]): Unit = {
    val p1 = new Person("tom",10)
    val p2 = new Person("jack",20)
    val compareComm2 = new CompareComm2(p1,p2)
    println(compareComm2.geatter.name)

    val cat1 = new Cat("Smith")
    val cat2 = new Cat("maryy1")
    println(new CompareComm2(cat1,cat2).geatter.name)
  }
}
class Person(val name:String,val age:Int)extends Ordered[Person]{
  //重写了Ordered的方法Compare
  override def compare(that: Person): Int = this.age - that.age

  override def toString: String = this.name +"\t"+this.age
}

//比较Cat的名字的长度大小
class Cat(val name:String) extends Ordered[Cat]{
  override def compare(that: Cat): Int = {
    this.name.length - that.name.length
  }
}

/**
  * 说明:
  * 1.T <% Ordered[T] 表示T是Ordered子类型java.lang.Compareabl
  * 2.这里调用的compareTo方法是T这个类型的方法
  * @param obj1
  * @param obj2
  * @param ev$1
  * @tparam T
  */
class CompareComm2[T <% Ordered[T]](obj1:T,obj2:T){
  def getter = if(obj1 > obj2) obj1 else obj2
  def geatter = if(obj1.compareTo(obj2)>0) obj1 else obj2
}