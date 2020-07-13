package test01

/**
  * @ProjectName Scala
  * @PackageName test01
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/7/10 - 17:24
  */
object comment {
  def main(args: Array[String]): Unit = {
    print("Hello")
  }

  /**
    * @deprecated
    * @param a
    * @param b
    * @example
    * a=10 b=20 a=b=30
    * @return 返回int类型的数值
    */
  def add(a: Int, b: Int): Int = {
    return a + b
  }
}
