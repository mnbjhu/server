/*package Models

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

@Serializable
data class Lobby(
    val host: Int,
    val players: List<Int>
)

object Lobbies: Table(){
    val id = integer("id").autoIncrement()
    val data = Lobbies.varchar("data", 512)

    override val primaryKey = PrimaryKey(id)

    fun createLobby(hostId: Int) = transaction{
        Lobbies.insert {
            it[data] = Json.encodeToString(Lobby(hostId, listOf()))
        }
    }
    fun getLobby(lobbyId: Int) = transaction {
        val raw = Lobbies.select{ Lobbies.id eq lobbyId }.first()[data]
        Json.decodeFromString<Lobby>(raw)
    }
    fun deleteLobby(lobbyId: Int) = transaction {
        deleteWhere { Lobbies.id eq lobbyId }
    }

    fun addUserToLobby(user: Int, lobby: Int){
        val current = getLobby(lobby)
        if(user == current.host || current.players.contains(user))
            throw Exception("User $user is already in the lobby $lobby")
        val data = Lobby(
            host = current.host,
            players = current.players + listOf(user)
        )
        transaction {
            Lobbies.update(where = { Lobbies.id eq lobby }){
                it[Lobbies.data] = Json.encodeToString(data)
            }
        }
    }
    fun kickUserFromLobby(user: Int, lobby: Int){
        val current = getLobby(lobby)
        val data = when (user){
            current.host -> Lobby(current.players.first(), current.players.drop(1))
            in current.players -> Lobby(current.host, current.players.filter{ it != user })
            else -> throw Exception("Couldn't kick user $user, not found in lobby $lobby")
        }
        transaction {
            Lobbies.update(where = { Lobbies.id eq lobby }){
                it[Lobbies.data] = Json.encodeToString(data)
            }
        }
    }
}

fun addLobbyRoutes(){

}*/