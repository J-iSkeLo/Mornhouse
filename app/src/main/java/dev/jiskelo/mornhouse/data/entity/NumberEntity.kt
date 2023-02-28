package dev.jiskelo.mornhouse.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import java.util.UUID

@Entity(tableName = "numbers_db")
data class NumberEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val number :Int,
    val description :String
)
