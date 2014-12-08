package it.paperdragon.scala.cake

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import it.paperdragon.scala.cake.HelloWorldActor.Message
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, Matchers}

import scala.concurrent.duration._

class HelloWorldActorSpec(_system: ActorSystem)
  extends TestKit(_system)
  with ImplicitSender
  with Matchers
  with FlatSpecLike
  with BeforeAndAfterAll {

  def this() = this(ActorSystem("TestSystem"))

  "A Hello World actor created with mocked settings" should "use mocked repository" in {

    // we inject custom implementation of a repository to the object
    val mockedApplication = new DefaultApplication with RepositoryComponent {
      override lazy val repository: Repository = new Repository {
        override def save(in: String): String = s"Saved by mocked repository: '$in'"
      }
    }

    val helloWorldActor = TestActorRef(Props(classOf[HelloWorldActor], mockedApplication))
    helloWorldActor ! Message("Test message")
    expectMsgType[String] should be("Saved by mocked repository: 'Test message'")
  }

  "A Hello World actor created with production settings" should "use production repository" in {
    val defaultApplication = new DefaultApplication()
    val helloWorldActor = TestActorRef(Props(classOf[HelloWorldActor], defaultApplication))
    helloWorldActor ! Message("Test message")
    expectMsgType[String] should be("Saved by production repository: 'Test message'")
  }

  override protected def afterAll(): Unit = {
    system.shutdown()
    system.awaitTermination(10.seconds)
  }

}
