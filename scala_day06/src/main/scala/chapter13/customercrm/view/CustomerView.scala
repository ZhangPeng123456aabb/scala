package chapter13.customercrm.view

import chapter13.customercrm.bean.Customer
import chapter13.customercrm.service.CustomerService
import util.control.Breaks._
import scala.io.StdIn

class CustomerView {
//定义一个循环变量，控制是否退出while
  var loop = true
  //定义一个key用于接收用户输入的的选项
  var key = ' '
  val CustomerService = new CustomerService()
  def mainMenu(): Unit ={
    do{
      println("--------------------客户信息管理软件-----------------------")
      println("            1. 添 加 客 户")
      println("            2. 修 改 客 户")
      println("            3. 删 除 客 户")
      println("            4. 客 户 列 表")
      println("            5. 退 出")
      println("            请选择(1-5):_")
      key = StdIn.readChar()
      key match {
        case '1' => this.add()
        case '2' => println(" 修 改 客 户 ")
        case '3' => this.del()
        case '4' => this.list()
        case '5' => this.loop = false
      }
    }while(loop)
    println("你退出了软件系统..")
  }
  def list(): Unit ={
    println()
    println("-----------------------客户列表-----------------------")
    println("编号\t\t姓名\t\t性别\t\t年龄\t\t电话\t\t邮箱")
    //for遍历
    //1.获取到CustomerService的customers ArrayBuffer
    val customers = CustomerService.list()
    for(customer <- customers){
      println(customer)
    }
    println("-------------------客户列表展示完成--------------------")
  }
  def add(): Unit ={
    println("-------------------添加客户---------------------------")
    println("姓名: ")
    val name = StdIn.readLine()
    println("姓别: ")
    val gender = StdIn.readChar()
    println("年龄: ")
    val age = StdIn.readShort()
    println("电话: ")
    val tel = StdIn.readLine()
    println("邮箱: ")
    val email = StdIn.readLine()

    //构建对象
    val customer = new Customer(name,gender,age,tel,email)
    CustomerService.add(customer)
    println("--------------------添加完成-------------------------")
  }
  def del(): Unit ={
    println("------------------删除客户-------------------------")
    println("请选择待删除客户编号(-1退出)：")
    val id = StdIn.readInt()
    if(id == -1){
      println("----------------删除没有完成---------------------")
      return
    }
    println("确认是否删除[Y/N]：")
    //要求用户在退出时提示"确认是否退出(Y/N)：",用户必须输入y/n,否则循环提示
    var choice = ' '
    breakable{
      do{
        choice = StdIn.readChar().toLower
        if(choice == 'y' || choice == 'n'){
          break()
        }
        println("确认是否删除(Y/N): ")
      }while(true)
    }
    if(choice == 'y'){
      if(CustomerService.del(id)){
        println("---------------删除完成------------------------")
        return
      }
    }
    println("------------------删除没有完成---------------------")
  }
}
