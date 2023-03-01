package dev.jiskelo.mornhouse.data.dao

import androidx.room.*
import dev.jiskelo.mornhouse.data.entity.NumberEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(numberEntity: NumberEntity)

    @Query("SELECT * FROM numbers_db ORDER BY id ASC")
    fun getAllNumbers(): Flow<List<NumberEntity>>

    @Delete
    fun delete(numberEntity: NumberEntity)

    @Update
    fun update(numberEntity: NumberEntity)

    @Query("DELETE FROM numbers_db")
    fun clearNumbers()
}