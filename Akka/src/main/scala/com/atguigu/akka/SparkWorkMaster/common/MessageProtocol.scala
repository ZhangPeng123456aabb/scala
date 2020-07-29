package com.atguigu.akka.SparkWorkMaster.common

//worker注册信息
case class RegisterWorkerInfo(id:String,cpu:Int,ram:Int)
/**
  *这个是WorkerInfo，保存在master的hashmap中的(该hashmap是用于管理worker)
  *将来这个WorkerInfo会扩展(比如增加Worker上一次的心跳时间)
  * @param id
  * @param cpu
  * @param ram
  */
class WorkerInfo(val id:String,val cpu:Int,val ram:Int){
  var lastHeartBeat:Long = System.currentTimeMillis()
}
//当worker注册成功，服务器返回一个RegisteredWorkerInfo对象
case object RegisteredWorkerInfo

//worker每隔一定时间由定时器发给自己的一个消息
case object SendHeartBeat
//worker每隔一定时间由定时器触发，而向master发送的协议消息
case class HeartBeat(id:String)
//master给自己发送一个触发检查超时worker的信息
case object StartTimeOutWorker
//master给自己发消息，检测worker,对于心跳超时的
case object RemoveTimeOutWorker
