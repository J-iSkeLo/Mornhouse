package dev.jiskelo.mornhouse.repositories

import dev.jiskelo.mornhouse.data.dao.NumberDao
import dev.jiskelo.mornhouse.data.entity.NumberEntity
import kotlinx.coroutines.flow.firstOrNull

class NumberRepository(private val numberDao: NumberDao) {

    fun fullNumbersEntity() = numberDao.getAllNumbers()

    suspend fun insert(numberEntity: NumberEntity) = numberDao.insert(numberEntity)

    suspend fun update(numberEntity: NumberEntity) = numberDao.update(numberEntity)

    suspend fun delete(numberEntity: NumberEntity) = numberDao.delete(numberEntity)

    suspend fun clearAllNumbersEntity() = numberDao.clearNumbers()
}