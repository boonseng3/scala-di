package com.sample.di

import com.escalatesoft.subcut.inject.NewBindingModule
import com.escalatesoft.subcut.inject.BindingModule
import com.escalatesoft.subcut.inject.{ NewBindingModule, BindingModule, Injectable }
object SampleSubcut extends App {
  val appModule = new SomeConfigurationModule

  import appModule._
  val userService = inject[UserService1](None)
  println(userService.getUserByUserName("test"))
}
trait UserService1 {
  def getUserByUserName(userName: String): Option[User1]
}

class SomeConfigurationModule extends NewBindingModule({ implicit module =>
  import module._
  if (false) {
    bind[UserService1] toSingle new SimpleUserService1
  } else {
    bind[UserService1] toSingle new TestSimpleUserService1
  }
  bind[OtherService1] toSingle new OtherService1
})

class TestSimpleUserService1(implicit val bindingModule: BindingModule) extends UserService1 with Injectable {
  val otherService = inject[OtherService1]
  private def Users = Map("test" -> User1("X54532", "Test", "User"))
  def getUserByUserName(userName: String) = {
    otherService.print
    Users get userName
  }
}
case class User1(customerNumber: String, firstName: String, lastName: String)

class SimpleUserService1(implicit val bindingModule: BindingModule) extends UserService1 with Injectable {
  val otherService = inject[OtherService1]
  private def Users = Map("real" -> User1("X54532", "Test", "User"))
  def getUserByUserName(userName: String) = {
    otherService.print
    Users get userName
  }
}

class OtherService1 {
  def print {
    println("print method invoked")
  }
}