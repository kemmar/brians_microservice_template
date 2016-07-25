package application

import com.twitter.finagle.Service
import com.twitter.finagle.http._
import controller.HelloWorldController.helloWorldRoute
import controller.MessageController._
import controller.RootController.rootRoute
import controller.UserController._
import domain.system.error.{ProcessingException, ServiceException}
import io.circe.generic.auto._
import io.finch.{Error, Output}
import io.finch.Output._
import io.finch.circe._

object RouteConfig {

  def routes: Service[Request, Response] = (
    rootRoute :+:
      helloWorldRoute :+:
      createUserRoute :+:
      createMessageRoute :+:
    listMessages
    ).handle({
    case e: Error =>
      createFailure(failure(new ProcessingException("error.bad.request", e.getMessage()), Status.BadRequest))

    case e: ProcessingException =>
      createFailure(failure(e, Status.UnprocessableEntity))

    case e: ServiceException =>
      createFailure(failure(e, Status.ServiceUnavailable))

    case e: Exception =>
      println(e)
      createFailure(failure(new ProcessingException("unknown.error", "an unknown error has accord"), Status.InternalServerError))
  }).toService

  def createFailure[A](e: Output[A]) = {
    e
      .withContentType(Some("application/json"))
  }


  def buildRoutes: Service[Request, Response] = routes
}
