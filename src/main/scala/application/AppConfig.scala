package application

import java.lang.System.getenv

import com.twitter
import com.twitter.finagle.http.Response
import com.typesafe.config.ConfigFactory
import domain.system.error.ProcessingException
import io.circe.Decoder
import io.circe.parser._

object AppConfig {
  val env = ConfigFactory
    .load()
    .withFallback(ConfigFactory.parseMap(System.getenv()))

  val conf = env.getConfig(s"${getenv("PROFILE")}").withFallback(env)

  lazy val port = conf.getString("PORT")
  lazy val dbPassword = conf.getString("DB_PASSWORD")
  lazy val dbUsername = conf.getString("DB_USERNAME")
  lazy val dbUrl = conf.getString("DB_URL")

  implicit class BetterResponses(response: twitter.util.Future[Response]) {
    def responseTo[T]()(implicit decoder: Decoder[T]) = {
      response.map { r =>
        decode[T](r.contentString).getOrElse(throw new ProcessingException("service.error", "unexpected response from service"))
      }
    }
  }

}
