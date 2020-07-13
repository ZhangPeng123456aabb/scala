package inherit

class Student {
  var id:Int=0
  var name:String=""
  val sex:Boolean=false
}
class SmallStudent extends Student{
  //覆盖父类成员
  //变量=新值
  id=1;
  name="small student"
  override val sex : Boolean = true
}
object SmallStudent{
  def apply(): SmallStudent = new SmallStudent()
  def main(args: Array[String]): Unit = {
    val s1 = SmallStudent()
    println(s1.id)
    println(s1.name)
    println(s1.sex)
  }
}