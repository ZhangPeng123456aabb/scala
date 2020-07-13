package inherit

import java.util.Date

class Person(var name:String)
//表示：在创建子类时，首先调用父类的有参主构造器
class Man (name:String,val birthday: Date)extends Person(name:String)
class color
class Green extends color()
object Green{
  def main(args: Array[String]): Unit = {

  }
}