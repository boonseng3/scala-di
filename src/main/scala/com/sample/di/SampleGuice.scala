package com.sample.di
import net.codingwell.scalaguice.InjectorExtensions._
import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Inject
object SampleGuice extends App {
  val injector = Guice.createInjector(new MyModule())

  val userService = injector.instance[UserService2]
  println(userService.getUserByUserName("test"))
}

class MyModule extends AbstractModule with ScalaModule {
  def configure {
    if(false){
      
    bind[UserService2].to[SimpleUserService2]
    } else {
      
    bind[UserService2].to[TestSimpleUserService2]
    }
  }
}

trait UserService2 {
  def getUserByUserName(userName: String): Option[User2]
}
class TestSimpleUserService2 @Inject()(injector: Injector) extends UserService2 {
  import net.codingwell.scalaguice.InjectorExtensions._
  val otherService = injector.instance[OtherService2]
  private def Users = Map("test" -> User2("X54532", "Test", "User"))
  def getUserByUserName(userName: String) = {
    otherService.print
    Users get userName
  }
}
case class User2(customerNumber: String, firstName: String, lastName: String)

class SimpleUserService2 @Inject()(injector: Injector) extends UserService2 {
  import net.codingwell.scalaguice.InjectorExtensions._
  val otherService = injector.instance[OtherService2]
  private def Users = Map("real" -> User2("X54532", "Test", "User"))
  def getUserByUserName(userName: String) = {
    otherService.print
    Users get userName
  }
}

class OtherService2 {
  def print {
    println("print method invoked")
  }
}