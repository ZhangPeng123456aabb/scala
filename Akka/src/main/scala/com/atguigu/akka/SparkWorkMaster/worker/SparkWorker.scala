package com.atguigu.akka.SparkWorkMaster.worker

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.atguigu.akka.SparkWorkMaster.common.{HeartBeat, RegisterWorkerInfo, RegisteredWorkerInfo, SendHeartBeat}
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._
class SparkWorker(masterHost:String,masterPort:Int,masterName:String) extends Actor {
  //masterProxy是Master的代理/引用ref
  var masterProxy: ActorSelection = _
  var id = java.util.UUID.randomUUID().toString
  override def preStart(): Unit = {
    println("preStart()调用")
    //初始化masterProxy
    masterProxy = context.actorSelection(s"akka.tcp://SparkMaster@$masterHost:$masterPort/user/$masterName")
    println("masterProxy= "+masterProxy)
  }

  override def receive: Receive = {
    case "start" => {
      println("worker启动了")
      masterProxy ! RegisterWorkerInfo(id,16,16*1024)
    }
    case RegisteredWorkerInfo => {
      println("workerid= "+id+"注册成功~~")
      //当注册成功后，就定义一个定时器，每隔一段时间，发送SendHeartBeat给自己
      import context.dispatcher
      /**
        * 说明
        * 1.0 millis 不延时，立即执行定时器
        * 2.3000 millis 表示每隔3秒执行一次
        * 3.self:表示发给自己
        * 4.SendHeartBeat 发送的内容
        */
      context.system.scheduler.schedule(0 millis,3000 millis,self,SendHeartBeat)

    }
    case SendHeartBeat => {
      println("worker = "+id+"给master发送心跳")
      masterProxy ! HeartBeat(id)
    }
  }
}
object SparkWorker{
  def main(args: Array[String]): Unit = {
    if(args.length != 6){
      println("请输入参数 workerHost workerPort workerName masterHost masterPort masterName")
      sys.exit()
    }
    val workerHost = args(0)
    val workerPort = args(1)
    val workerName = args(2)
    val masterHost = args(3)
    val masterPort = args(4)
    val masterName = args(5)

    val config = ConfigFactory.parseString(
      s"""
         |akka.actor.provider="akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname=${workerHost}
         |akka.remote.netty.tcp.port=${workerPort}
       """.stripMargin
    )
    //先创建ActorSystem
    val sparkWorkerSystem = ActorSystem("SparkWorker",config)
    val SparkWorkerRef = sparkWorkerSystem.actorOf(Props(new SparkWorker(masterHost,masterPort.toInt,masterName)),s"${workerName}")
    SparkWorkerRef ! "start"
  }
}
