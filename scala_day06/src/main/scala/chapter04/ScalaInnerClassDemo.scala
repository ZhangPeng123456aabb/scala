package chapter04

/**
  * @ProjectName Scala
  * @PackageName chapter04
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/17 - 11:17
  */
object ScalaInnerClassDemo {
  def main(args: Array[String]): Unit = {
    val outer1 = new ScalaOuterClass()
    val outer2 = new ScalaOuterClass()
    //在scala中，创建成员内部类的语法是
    //对象.内部类的方式创建，这里的语法可以看出在scala中，默认情况下内部类实例和外部类对象关联
    val inner1 = new outer1.ScalaInnerClass
    val inner2 = new outer2.ScalaInnerClass
    inner1.info()
    inner2.info()
    inner1.test(inner1)
    inner2.test(inner2)

    //创建静态内部类实例
    val staticInner = new ScalaOuterClass.ScalaStaticInnerClass()
  }
}
//外部类
class ScalaOuterClass{
  //给外部类起别名
  myouter =>
  class ScalaInnerClass{//成员内部类
  def info() = {
    //访问方式:外部类名.this.属性名
    //怎么理解 ScalaOuterClass.this 就相当于是scalaOuterClass这个外部类的实例
    //然后通过ScalaOuterClass.this 实例对象去访问name属性
    //只是这种写法比较特别，学习java的同学可能更容易理解scalaOuterClass.class的写法
    println("name="+myouter.name+"  "+"age="+myouter.sal)
  }
    //下面的scalaOuterClass#ScalaInnerClass类型投影的作用就是屏蔽外部类对象的影响
    def test(ic : ScalaOuterClass#ScalaInnerClass):Unit={
      System.out.println("使用了类型投影 "+ic)
    }
  }
  //定义两个属性
  var name = "scoot"
  private var sal = 30000.9
}
object ScalaOuterClass{//伴生对象
class ScalaStaticInnerClass{//静态内部类
  }
}
