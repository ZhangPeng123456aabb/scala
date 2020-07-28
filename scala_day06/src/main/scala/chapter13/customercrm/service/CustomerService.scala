package chapter13.customercrm.service

import chapter13.customercrm.bean.Customer

import scala.collection.mutable.ArrayBuffer
import util.control.Breaks._
class CustomerService {
  var customerNum = 1
//customers是存放客户列表，为了测试，这里先初始化一个
  val customers = ArrayBuffer(new Customer(1,"tom",'男',10,"110","Tom@sohu.com"))
  def list(): ArrayBuffer[Customer]={
    this.customers
  }
  //添加新客户
  def add(customer:Customer):Boolean={
    //设置id
    customerNum += 1
    customer.id = customerNum
    //加入到customers中
    customers.append(customer)
    true
  }
  def del(id:Int):Boolean={
    val index = findIndexById(id)
    if(index != -1){
      //删除
      customers.remove(index)
      true
    }else{
      false
    }
  }
  //根据id找到index
  def findIndexById(id:Int)={
    var index = -1 //默认-1，如果找到就改成对应的，如果没有找到，就返回-1
    //遍历customer
    breakable {
      for(i <- 0 until customers.length){
        if(customers(i).id == id){//找到
          index = i
          break()
        }
      }
    }
    index
  }
}
