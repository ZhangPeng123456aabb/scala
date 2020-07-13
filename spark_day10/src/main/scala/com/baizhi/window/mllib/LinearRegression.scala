package com.baizhi.window.mllib

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.regression
import org.apache.spark.mllib.regression.LinearRegressionWithSGD
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ProjectName Scala
  * @PackageName com.baizhi.window.mllib
  * @author ZhangPeng
  * @Email ZhangPeng1853093@126.com
  * @date 2020/4/15 - 16:16
  */
object LinearRegression {
  def main(args: Array[String]): Unit = {
    //屏蔽日志
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    //创建SparkContext
    val conf = new SparkConf().setMaster("local[*]").setAppName("LinearRegression")
    val sc = new SparkContext(conf)
    //加载数据样本
    val path = "F:\\study\\IDEA_project\\Scala\\spark_day10\\src\\main\\resources\\data.txt"
    //通过提供的工具类加载样本数据
    val data = MLUtils.loadLibSVMFile(sc,path).cache()
    //迭代次数
    val numlterations = 100
    //梯度下降步长
    val stepSize = 0.00000001
    //训练模型
    val model = LinearRegressionWithSGD.train(data,numlterations,stepSize)
    //模型评估
    val valuesAndPreds=data.map{point=>
      //根据模型评估
      val prediction=model.predict(point.features)
      println(s"[真实值]:${point.label}        :[预测值]:${prediction}")
      (point.label,prediction)
    }
    //求均方误差
    val MSE = valuesAndPreds.map{case(v,p)=>math.pow((v-p),2)}.mean()
    println("训练模型的均方差为= "+MSE)
    //保存模型
    model.save(sc,"F:\\study\\IDEA_project\\Scala\\spark_day10\\src\\main\\resources\\model")
    val sameModel = regression.LinearRegressionModel.load(sc,"F:\\study\\IDEA_project\\Scala\\spark_day10\\src\\main\\resources\\model")
    sc.stop()
  }
}
