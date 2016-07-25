package application

import com.twitter.finagle.Service
import com.twitter.finagle.http._
import domain.system.error.{ProcessingException, ServiceException}
import io.finch.{Endpoint, Error, Output}
import io.finch.Output._

trait RouteConfig {

  def routeErrorHandler[B]: Endpoint[B] => Endpoint[B] = route => route.handle {
    case e: Error =>
      createFailure(failure(new ProcessingException("error.bad.request", e.getMessage()), Status.BadRequest))

    case e: ProcessingException =>
      createFailure(failure(e, Status.UnprocessableEntity))

    case e: ServiceException =>
      createFailure(failure(e, Status.ServiceUnavailable))

    case e: Exception =>
      println(e)
      createFailure(failure(new ProcessingException("unknown.error", "an unknown error has accord"), Status.InternalServerError))
  }

  def createFailure[A](e: Output[A]) = {
    e
      .withContentType(Some("application/json"))
  }


  def buildRoutes: Service[Request, Response]
}
