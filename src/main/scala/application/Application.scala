package application

import com.twitter.finagle.Http
import com.twitter.util.Await

object Application extends App {
  println(s"connecting to port: ${AppConfig.port}")
  Await.ready(Http.server.serve(s":${AppConfig.port}", RouteConfig.buildRoutes))
}
