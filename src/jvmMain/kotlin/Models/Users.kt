package Models


import org.jetbrains.exposed.sql.Table

object Users: Table() {
    val id = integer("id").autoIncrement()
    val username = varchar("username", 32)
    val password = varchar("password", 60)
    val first_name =  varchar("first_name", 32)
    val last_name =  varchar("last_name", 32)
    val email =  varchar("email", 128)
    override val primaryKey = PrimaryKey(id)

}
