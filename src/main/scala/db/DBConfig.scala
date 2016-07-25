package db

import application.AppConfig._
import net.fwbrasil.activate.ActivateContext
import net.fwbrasil.activate.storage.relational.PooledJdbcRelationalStorage
import net.fwbrasil.activate.storage.relational.idiom.mySqlDialect

object DBConfig extends ActivateContext {
  override protected def entitiesPackages = List("domain")
  val storage = new PooledJdbcRelationalStorage {

    override val jdbcDriver = "com.mysql.jdbc.Driver"
    override val user = Some(dbUsername)
    override val password = Some(dbPassword)
    override val url = s"jdbc:$dbUrl"
    override val dialect = mySqlDialect
  }
}
