package dev.jiskelo.netronic.data.dao

import androidx.room.*
import dev.jiskelo.netronic.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Query("SELECT * FROM users_db ORDER BY id ASC")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users_db WHERE id=:id")
    fun getUserById(id :String) :UserEntity

    @Delete
    fun delete(userEntity: UserEntity)

    @Update
    fun update(userEntity: UserEntity)

    @Query("DELETE FROM users_db")
    fun clearUsers()
}