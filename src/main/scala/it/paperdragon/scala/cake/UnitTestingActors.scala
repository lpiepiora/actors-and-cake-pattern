package it.paperdragon.scala.cake

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._


object UnitTestingActors extends App {
  val system = ActorSystem("main")

  val productionApplication = new DefaultApplication()

  val helloWorldActor = system.actorOf(Props(classOf[HelloWorldActor], productionApplication), "hello-world-actor")

  implicit val timeout = Timeout(5.minutes)
  val responseFuture = (helloWorldActor ? HelloWorldActor.Message("Hello from Main")).mapTo[String]
  val response = Await.result(responseFuture, timeout.duration)

  println(s"Response: '$response'")

  system.shutdown()

}
