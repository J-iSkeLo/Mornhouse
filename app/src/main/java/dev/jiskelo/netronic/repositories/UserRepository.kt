package dev.jiskelo.netronic.repositories

import dev.jiskelo.netronic.data.entity.UserEntity
import dev.jiskelo.netronic.structs.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(results :Int) :List<User>

    suspend fun getUserById(id :String) :UserEntity?

    suspend fun getHistoryUsers() : Flow<List<UserEntity>>

    fun mapUserToUserEntity(user: User) :UserEntity

    suspend fun saveUserToDatabase(users: List<User>)
}