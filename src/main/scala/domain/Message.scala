package domain

import net.fwbrasil.activate.entity.Entity

case class Message(author: User, message: String) extends Entity

case class MessageReq(author: String, message: String)
