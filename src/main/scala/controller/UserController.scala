package controller

import db.DBConfig._
import domain.{User, UserReq}
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._

object UserController {

  lazy val createUserRoute =  post("user" :: body.as[UserReq]) { user: UserReq =>
      Ok {
        transactional {
          (User.apply _).tupled  (UserReq.unapply(user).get)
        }
    }
  }

}