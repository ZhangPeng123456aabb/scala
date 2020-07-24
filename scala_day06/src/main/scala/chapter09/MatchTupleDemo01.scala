package chapter09

object MatchTupleDemo01 {
  def main(args: Array[String]): Unit = {
    for(pair <- Array((0,1),(1,0),(10,30),(1,1),(1,0,2))){
      val result = pair match{
        case (0,_) => "0....."
        case (y,0) => y
        case (x,y) => (y,x)
        case _ => "other"
      }

      /**
        * 结果:
        result= 0.....
        result= 1
        result= (30,10)
        result= (1,1)
        result= other
        */
      println("result= "+result)
    }
  }
}
