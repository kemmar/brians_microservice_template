package db.migration

import db.DBConfig.context
import domain.{Message, User}
import net.fwbrasil.activate.migration.Migration

class CreateMessageTable extends Migration {
  override def timestamp: Long = 1465754036000L

  override def up = {
    table[Message]
      .createTable(
        _.column[User]("author"),
        _.column[String]("message")
      )
  }

  override def down = {
    table[Message]
      .removeTable
  }
}
