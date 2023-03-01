package dev.jiskelo.netronic.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_db")
data class UserEntity(

    @PrimaryKey
    val id: String,
    val image :String,
    val name :String,
    val gender :String,
    val email :String,
    val phone :String,
    val dateOfBirthday :String,
    val createAt :Long
)
