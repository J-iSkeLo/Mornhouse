package dev.jiskelo.mornhouse.util

import com.google.gson.Gson
import dev.jiskelo.mornhouse.data.entity.NumberEntity

fun NumberEntity.entityToJson(): String = Gson().toJson(this)
fun String.jsonToEntity() : NumberEntity? = Gson().fromJson(this, NumberEntity::class.java)