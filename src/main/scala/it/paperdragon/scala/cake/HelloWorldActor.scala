package it.paperdragon.scala.cake

import akka.actor.Actor


object HelloWorldActor {

  case class Message(in: String)

}

// this is the actual actor. The actor requires
class HelloWorldActor(app: Application) extends Actor {

  import HelloWorldActor._

  def receive = {
    case Message(in) =>
      sender ! app.service.createStuff(in)
  }

}