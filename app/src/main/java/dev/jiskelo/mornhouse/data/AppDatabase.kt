package dev.jiskelo.mornhouse.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.jiskelo.mornhouse.data.dao.NumberDao
import dev.jiskelo.mornhouse.data.entity.NumberEntity

@Database(entities = [NumberEntity::class], version = 1, exportSchema = false )
abstract class AppDatabase : RoomDatabase() {

    abstract fun numberDao() : NumberDao

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
                    "numbers_db"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
