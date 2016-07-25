package controller

import db.DBConfig._
import domain.{Message, MessageReq, User}
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._

object MessageController {

  lazy val createMessageRoute = post("message" :: body.as[MessageReq]) { msg: MessageReq =>
    Created {
      transactional {
        Message((select[User] where (u => u.id :== msg.author)).head, msg.message)
      }
    }
  }

  lazy val listMessages: Endpoint[List[Message]] = get("message" :: param("user")) { user: String =>
    Ok {
      transactional {
        select[Message] where (msg => msg.author :== user)
      }
    }
  }

}
