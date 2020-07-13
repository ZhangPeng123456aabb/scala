package patternmatch

import java.util.Date

/**
  * 模式匹配中的 类型匹配 判断类型是否兼容 注意事项：子类需要写在父类前面
  */
object Pattern4 {
  def main(args: Array[String]): Unit = {
    val t1 = new Date
    val t2 = new Person
    val t3 = new Worker
    val t4 = new Student
    t4 match{
      case y:Student =>println("student")
      case z:Worker =>println("worker")
      case x:Person =>println("person")
      case _=>println("其他类型")
    }
  }
}
class Person
class Student extends Person
class Worker extends Person
