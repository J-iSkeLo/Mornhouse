package dev.jiskelo.netronic.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.jiskelo.netronic.data.dao.UserDao
import dev.jiskelo.netronic.data.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false )
abstract class AppDatabase : RoomDatabase() {

    abstract fun numberDao() : UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase (context: Context) : AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "users_db"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
