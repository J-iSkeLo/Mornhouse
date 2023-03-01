package dev.jiskelo.netronic.repositories

import android.util.Log
import dev.jiskelo.netronic.data.dao.UserDao
import dev.jiskelo.netronic.data.entity.UserEntity
import dev.jiskelo.netronic.network.UserService
import dev.jiskelo.netronic.structs.Response
import dev.jiskelo.netronic.structs.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import retrofit2.Call
import retrofit2.Callback
import java.util.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val usersDao :UserDao
) :UserRepository {

    override suspend fun getUsers(results: Int): List<User> {
        return userService.getUsers(results).results
    }

    override suspend fun getUserById(id: String): UserEntity? {
        return usersDao.getUserById(id)
    }

    override suspend fun getHistoryUsers(): Flow<List<UserEntity>> {
        return usersDao.getAllUsers()
    }

    override suspend fun saveUserToDatabase(users: List<User>) {
        users.forEach { user ->
            val userEntity = mapUserToUserEntity(user)
            usersDao.insert(userEntity)
        }
    }

    override fun mapUserToUserEntity(user: User): UserEntity {
        return UserEntity(
            id = user.login.uuid,
            name = user.name.getName(),
            image = user.picture.large,
            gender = user.gender,
            email = user.email,
            phone = user.phone,
            dateOfBirthday = user.dob.date,
            createAt = System.currentTimeMillis()
        )
    }
}