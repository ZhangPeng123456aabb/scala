package com.atguigu.akka.yellowchicken.client

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}
import com.atguigu.akka.yellowchicken.common.{ClientMessage, ServerMessage}
import com.typesafe.config.ConfigFactory

import scala.io.StdIn

class CustomerActor(serverHost:String,serverPort:Int) extends Actor {
  //定义一个YellowChickenServerRef
  var serverActorRef: ActorSelection = _

  /**
    * 在Actor中有一个方法PreStart方法，他会在actor运行前执行
    * 在akka开发中，通常将初始化的工作，放在preStart方法
    */
  override def preStart():Unit = {
    println("preStart() 执行")
    serverActorRef = context.actorSelection(s"akka.tcp://Server@${serverHost}:${serverPort}/user/YellowChickenServer")
    println("serverActorRef= "+serverActorRef)
  }
  override def receive: Receive = {
    case "start" => println("客户端开始运行了，可以咨询问题啦")
    case mes:String =>{
      //发送给小黄鸡客服
      serverActorRef ! ClientMessage(mes)//使用clientMessage case class apply
    }
      //如果接收到服务器的回复
    case ServerMessage(mes) => {
      println("接收到小黄鸡客服人员的回复: "+mes)
    }
  }
}
//主程序-入口
object CustomerActor extends App{
  val (clientHost,clientPort,serverHost,serverPort) = ("127.0.0.1",9990,"127.0.0.1",9999)
  val config = ConfigFactory.parseString(
    s"""
       |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
       |akka.remote.netty.tcp.hostname=$clientHost
       |akka.remote.netty.tcp.port=$clientPort
     """.stripMargin)
    //创建ActorSystem
    val clientActorSystem = ActorSystem("client",config)
    //创建CustomerActor的实例和引用
  val customerActorRef: ActorRef = clientActorSystem.actorOf(Props(new CustomerActor(serverHost,serverPort)),"CustomerActor")

  customerActorRef ! "start"
  //客户端可以发送消息给服务器
  while(true){
    println("请输入想要咨询的问题")
    val mes = StdIn.readLine()
    customerActorRef ! mes
  }
}
