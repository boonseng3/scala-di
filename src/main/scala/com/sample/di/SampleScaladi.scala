package com.sample.di

import scaldi.Module
import scala.concurrent.duration._
import scala.language.postfixOps
import scaldi.Injector
import scaldi.Injectable._

object SampleScaladi extends App {
  implicit val appModule = new UserModule

  val userService = inject[UserService]

  println(userService.getUserByUserName("test"))
}

class UserModule extends Module {
  if (false) {

    bind[UserService] to new SimpleUserService
  } else {
    bind[UserService] to new TestSimpleUserService
  }

  bind[OtherService] to new OtherService
}

trait UserService {
  def getUserByUserName(userName: String): Option[User]
}
class TestSimpleUserService(implicit inj: Injector) extends UserService {
  val otherService = inject[OtherService]
  private def Users = Map("test" -> User("X54532", "Test", "User"))
  def getUserByUserName(userName: String) = {
    otherService.print
    Users get userName
  }
}
case class User(customerNumber: String, firstName: String, lastName: String)

class SimpleUserService(implicit inj: Injector) extends UserService {
  val otherService = inject[OtherService]
  private def Users = Map("real" -> User("X54532", "Test", "User"))
  def getUserByUserName(userName: String) = {
    otherService.print
    Users get userName
  }
}

class OtherService {
  def print {
    println("print method invoked")
  }
}