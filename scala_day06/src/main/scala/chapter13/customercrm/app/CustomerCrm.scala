package chapter13.customercrm.app

import chapter13.customercrm.view.CustomerView

object CustomerCrm {
  def main(args: Array[String]): Unit = {
    new CustomerView().mainMenu()
  }
}
