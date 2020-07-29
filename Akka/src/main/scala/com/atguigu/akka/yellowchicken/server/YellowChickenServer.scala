package com.atguigu.akka.yellowchicken.server

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.atguigu.akka.yellowchicken.common.{ClientMessage, ServerMessage}
import com.typesafe.config.ConfigFactory

class YellowChickenServer extends Actor {
  override def receive: Receive = {
    case "start" => println("start 小黄鸡客服开始工作了....")
    case ClientMessage(mes) =>{
      //使用match --case 匹配(模糊)
      mes match {
        case "大数据学费" => sender() ! ServerMessage("35000RMB")
        case "学校地址" => sender() ! ServerMessage("杭州市滨江区杭州印")
        case "有什么课程" => sender() ! ServerMessage("JAVA Python 前端")
        case _ => sender() ! ServerMessage("你说的什么，听不懂")
      }
    }
  }
}
//主程序-入口
object YellowChickenServer extends App{
  val host = "127.0.0.1" //服务器ip地址
  val port = 9999
  //创建config对象，指定协议类型，监听的ip和端口
  val config = ConfigFactory.parseString(
    s"""
       |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
       |akka.remote.netty.tcp.hostname=$host
       |akka.remote.netty.tcp.port=$port
     """.stripMargin
  )
  /**
    *创建ActorSystem
    * url(统一资源定位)
    */
  val serverActorSystem = ActorSystem("Server",config)
  //创建YellowChickenServer 的actor和返回actorRef
  val yellowChickenServer: ActorRef = serverActorSystem.actorOf(Props[YellowChickenServer],"YellowChickenServer")

  yellowChickenServer ! "start"
}