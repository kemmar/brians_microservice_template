package domain.system.error

import domain.system.FiirbException

case class ProcessingException(code: String, message: String) extends FiirbException
