package com.atguigu.akka.SparkWorkMaster.master
import akka.actor.{Actor, ActorSystem, Props}
import com.atguigu.akka.SparkWorkMaster.common._
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration._
import scala.collection.mutable
class SparkMaster extends Actor {
  //定义一个hm,管理workers
  val workers = mutable.Map[String,WorkerInfo]()
  override def receive: Receive = {
    case "start" =>{
      println("master服务器启动了...")
      //这里开始..
      self ! StartTimeOutWorker
    }
    case RegisterWorkerInfo(id,cpu,ram)=>{
      //接收到worker注册信息
      if(!workers.contains(id)){
        //创建WorkerInfo对象
        val workerInfo = new WorkerInfo(id,cpu,ram)
        //加入到workers
        workers += ((id,workerInfo))
        println("服务器workers= "+workers)
        //回复一个消息，说注册成功
        sender() ! RegisteredWorkerInfo
      }
    }
    case HeartBeat(id) => {
      /**
        * 更新对应的worker的心跳时间
        * 1.从workers取出workerInfo
        */
      val workerInfo = workers(id)
      workerInfo.lastHeartBeat = System.currentTimeMillis()
      println("master更新了 "+id+" 心跳时间....")
    }
    case StartTimeOutWorker => {
      println("开始了定时检测worker心跳的任务")
      import context.dispatcher
      /**
        * 说明
        * 1.0 millis 不延时，立即执行定时器
        * 2.3000 millis 表示每隔3秒执行一次
        * 3.self:表示发给自己
        * 4.SendHeartBeat 发送的内容
        */
      context.system.scheduler.schedule(0 millis,9000 millis,self,RemoveTimeOutWorker)
    }

    /**
      * 对RemoveTimeOutWorker消息处理
      * 这里需求检测哪些worker心跳超时(now - lastHeartBeat > 6000),并从map中删除
      */
    case RemoveTimeOutWorker => {
      //首先将所有的workers的所有workerInfo
      val workerInfos = workers.values
      val nowTime = System.currentTimeMillis()
      //先把超时的所有workerInfo,删除即可
      workerInfos.filter(workerInfos => (nowTime - workerInfos.lastHeartBeat) > 6000)
        .foreach(workerInfos=>workers.remove(workerInfos.id))
      println("当前有 "+workers.size+"个worker存活")
    }
  }
}
object SparkMaster{
  def main(args: Array[String]): Unit = {
    //先创建ActorSystem
    val config = ConfigFactory.parseString(
      s"""
         |akka.actor.provider="akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname=127.0.0.1
         |akka.remote.netty.tcp.port=10005
       """.stripMargin
    )
    val sparkMasterSystem = ActorSystem("SparkMaster",config)
    val sparkMasterRef = sparkMasterSystem.actorOf(Props[SparkMaster],"SparkMaster-01")
    //启动SparkMaster
    sparkMasterRef ! "start"
  }
}
