package Models


import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users: Table() {
    val id = integer("id").autoIncrement()
    val username = varchar("username", 32)
    val password = varchar("password", 60)
    val first_name =  varchar("first_name", 32)
    val last_name =  varchar("last_name", 32)
    val email =  varchar("email", 128)
    override val primaryKey = PrimaryKey(id)
}
@Serializable
data class User(
    val id: Int,
    val username: String,
    val firstName: String,
    val secondName: String,
    val email: String
)
fun getUserById(id: Int): User = transaction {
    val query = Users
        .select { Users.id eq id }
        .single()
    User(
        query[Users.id],
        query[Users.username],
        query[Users.first_name],
        query[Users.last_name],
        query[Users.email]
    )


}