package controller

import com.twitter.finagle.http
import com.twitter.finagle.http.Method._
import domain.Greeting
import domain.system.error.ProcessingException
import io.circe.generic.auto._
import io.finch._
import service.LocalService._
import application.AppConfig.BetterResponses

object RootController {

  val rootRoute =
    get(/) {
      Ok(
        runTasks
      )
    }

  def runTasks = {
    sendRequest[Greeting](http.Request(Put, getConfig("hello")))
      .responseTo[ProcessingException]
  }
}
