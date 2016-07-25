package domain.system.error

import domain.system.FiirbException

case class ServiceException(code: String, message: String) extends FiirbException
