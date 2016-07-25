package application

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{Http, Service}
import domain.system.error.ServiceException

abstract class ServiceConfig {

  val serviceName: String

  def url: String = AppConfig.conf.getString(s"$serviceName.url")

  def getConfig(path: String): String = AppConfig.conf.getString(s"$serviceName.$path")

  def service: Service[Request, Response] = Http.newService(url)

  def sendRequest[T](request: Request) = {
    service(request)
      .handle {
        case e: Exception => throw new ServiceException("service.error", s"unable to connect to service: $serviceName")
      }
  }
}


