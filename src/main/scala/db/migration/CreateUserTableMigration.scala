package db.migration

import net.fwbrasil.activate.migration.Migration
import db.DBConfig.context
import domain.User

class CreateUserTableMigration extends Migration {
  override def timestamp: Long = 1465753683000L

  override def up = {
    table[User]
      .createTable(
        _.column[String]("firstname"),
        _.column[String]("lastname")
      )
  }

  override def down = {
    table[User]
      .removeTable
  }
}
