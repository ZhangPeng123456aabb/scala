package com.baizhi

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object TransformationsTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("transformation Test")
    val ssc = new StreamingContext(conf,Seconds(5))
    //构建DStream(离散数据流)
    //val dstream = ssc.socketTextStream("spark",6666)
    ssc.sparkContext.setLogLevel("ERROR")
    //操作算子
    //======================filter=======================
//    dstream
//      //将一个数据展开为1到多个数据
//      .flatMap(str=>str.split(" "))
//      //动态调整分区数
//      .repartition(5)
//      //将一种类型转换为另外一种类型
//      .map(word=>(word,1))
//      //保留符合条件的结果
//      .filter(t=> !t._1.equals("Hello"))
//      //将key相同的两个相邻的value进行相加
//      .reduceByKey((v1,v2)=>v1+v2)
//      //输出 将DStream处理结果中的前10条记录输出到console
//      .print()
    //=======================union==================================
    //union 联合结合 将两个DStream内的元素合并为一个Dstream
//    val dstream1 = ssc.socketTextStream("spark",7777)
//    val dstream2 = ssc.socketTextStream("spark",8888)
//    dstream1.union(dstream2).print()
    //=========================count=============================
//    val dstream3 = ssc.socketTextStream("spark",7777)
//    //返回RDD中元素的个数
//    dstream3.count().print()
    //============================reduce=====================
//    val dstream4=ssc.socketTextStream("spark",7777)
//    dstream4
//        .map(strNum=>strNum.toInt)
//        //reduce咋进行计算时，相邻的两个元素的能支持数学计算
//        .reduce((v1,v2)=>v1+v2)
//         //数值之和
        //.print()
    //==============================countByValue=============================
//    val dstream5 = ssc.socketTextStream("spark",7777)
//    dstream5
//        .flatMap(_.split(" "))
//         //统计micro batch 中相同的元素出现的个数(word.Long)
//        .countByValue()
//        .print()
    //==================================join========================
    //join两个Dstream连接的 返回一个（k,(v,w)）
//
      //ssc.start()val dstream6 = ssc.socketTextStream("spark",7777).flatMap(_.split(" ")).map((_,1))
    //    val dstream7 = ssc.socketTextStream("spark",8888).flatMap(_.split(" ")).map((_,1))
    //    dstream6.join(dstream7).print()
    //===================================cogroup===================================
    //cogroup两个Dstream连接的 返回一个(k,seq[v],seq[w])
//    val dStream8 = ssc.socketTextStream("spark", 7777).flatMap(_.split(" ")).map((_, 1))
    //    val dStream9 = ssc.socketTextStream("spark", 8888).flatMap(_.split(" ")).map((_, 1))
    //    dStream8.cogroup(dStream9).print()
    //=====================================transform=============================
    //如下需求：
    // 某系统 需求开发一个抽奖系统，要求黑名单的用户不允许抽奖
    // 首先：不断产生抽奖请求（白名单  + 黑名单）  Stream
    // 其次：黑名单用户(batch)
    // Stream +　Batch
    // 最后：只保留白名单用户抽奖请求
    //userid requestURL[抽奖请求的资源地址]
//    val requestStream = ssc.socketTextStream("spark",9999)
//    val blackList = List(("001","ls"),("002","zs"),("003","zy"))
//    val blackListRDD = ssc.sparkContext.makeRDD(blackList)
//    requestStream
//        .map(line=>{
//          val arr = line.split(" ")
//          val userId=arr(0)
//          val requestUrl=arr(1)
//          (userId,requestUrl)
//        })
//        .transform(mapTransformRDD=>{
//          mapTransformRDD.leftOuterJoin(blackListRDD)//对转换后的RDD和黑名单的RDD
//            .filter(t=>t._2._2.isEmpty)//判断连接后 是否有值 None
//        }).print
    //============================updateStateByKey===========================================
    ssc.checkpoint("hdfs://spark:9000/checkpoint2")
    val dstream10 = ssc.socketTextStream("spark",9999)
    dstream10
        .flatMap(_.split(" "))
        .map((_,1))
        //状态数据 k,v
        //values :代表微批RDD中 key 相同的values的集合
        //state:累计的状态数据
        .updateStateByKey((values:Seq[Int],state:Option[Int])=>{
         Some(values.size+state.getOrElse(0))//None没有值
         //返回一个带状态的DStream通俗理解：状态的去全量输出（状态数据）
    }).print()
      ssc.start()
      ssc.awaitTermination()
  }
}
