package controller

import domain.{Greeting, Name}
import io.finch._
import io.finch.circe._
import io.circe.generic.auto._

object HelloWorldController {
  val helloWorldRoute =
    put("hello" :: body.as[Name]) { req: Name =>
      Ok {
        Greeting(req.name)
      }
    }
}
