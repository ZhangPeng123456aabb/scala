package com.atguigu.akka.actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
  * 说明
  * 1.当我们继承Actor后，就是一个Actor，核心方法receive方法重写
  */
class SayHelloActor extends Actor {
  /**
    * 说明
    * 1.receive方法，会被该Actor的MailBox(实现了Runnable接口)调用
    * 2.当该Actor的MailBox接收到消息，就会调用receive
    * 3.type Receive = PartialFunction[Any,Unit]
    * @return
    */
  override def receive: Receive = {
    case "hello" => println("收到hello,回应hello too:)")
    case "ok" => println("收到ok,回应ok too:)")
    case "exit" => {
      println("接收到exit指令，退出系统")
      context.stop(self) //退出actorRef
      context.system.terminate()//退出ActorSystem
    }
    case _ => println("匹配不到")
  }
}
object SayHelloActor{
  /**
    * 先创建一个ActorSystem,专门用于创建Actor
    */
  private val actoryFactory = ActorSystem("actoryFactory")
  /**
    * 创建一个Actor的同时，返回Actor的ActorRef
    * 说明:
    * (1)Props[SayHelloActor]创建一个SayHelloActor实例，使用反射
    * (2)"sayHelloActor"给actor取名
    * (3)sayHelloActorRef:ActorRef就是Props[SayHelloActor]的ActorRef
    * (4)创建SayHelloActor 实例被
    */
  private val sayHelloActorRef: ActorRef = actoryFactory.actorOf(Props[SayHelloActor],"SayHelloActor")

  def main(args: Array[String]): Unit = {
    //给SayHelloActor发消息(发邮箱)
    sayHelloActorRef ! "hello"
    sayHelloActorRef ! "ok"
    sayHelloActorRef ! "ok~"
    //研究如何退出ActorSystem
    sayHelloActorRef ! "exit"
  }
}