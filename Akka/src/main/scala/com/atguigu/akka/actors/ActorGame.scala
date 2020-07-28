package com.atguigu.akka.actors

import akka.actor.{ActorRef, ActorSystem, Props}

object ActorGame extends App {
  //创建ActorSystem
  val actoryfactory = ActorSystem("actorfactory")
  //先创建BActor引用/代理
  val bActorRef:ActorRef = actoryfactory.actorOf(Props[BActor],"bActor")
  //创建AActor的引用
  val aActorRef: ActorRef = actoryfactory.actorOf(Props(new AActor(bActorRef)),"aActor")

  //aActor出招
  aActorRef ! "start"
}
