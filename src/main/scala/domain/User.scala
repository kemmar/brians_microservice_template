package domain

import net.fwbrasil.activate.entity.Entity

case class User(firstName: String, lastName: String) extends Entity

case class UserReq(firstName: String, lastName: String)