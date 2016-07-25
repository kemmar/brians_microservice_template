package domain.system

abstract class FiirbException extends Exception {
  val code: String
  val message: String
  override def getMessage: String = s"""{"code": "$code", "message":"$message"}"""
}