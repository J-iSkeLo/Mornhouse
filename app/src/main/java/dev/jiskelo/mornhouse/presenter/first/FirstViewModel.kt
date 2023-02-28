package dev.jiskelo.mornhouse.presenter.first

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jiskelo.mornhouse.data.entity.NumberEntity
import dev.jiskelo.mornhouse.network.NumbersApi
import dev.jiskelo.mornhouse.repositories.NumberRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class FirstViewModel(
    private val api: NumbersApi,
    private val repository: NumberRepository
) : ViewModel() {

    fun fullNumbersEntity() :Flow<List<NumberEntity>> = repository.fullNumbersEntity()

    fun getNumbersDescription(
        numberString :String,
        result : (NumberEntity) -> Unit
    ) {
        val number = numberString.toInt()
        viewModelScope.launch(IO) {
            api.getNumber(number).also {
                val numberEntity = NumberEntity(
                    id = 0L,
                    number = number,
                    description = it
                )
                repository.insert(numberEntity)
                withContext(Main) {
                    result(numberEntity)
                }
            }
        }
    }

    fun getRandomNumbersDescription(
        result : (NumberEntity) -> Unit
    ) {
        viewModelScope.launch(IO) {
            api.getRandomNumberFacts().also {
                val number = it.split(" ", ignoreCase = false).first()

                Log.e("Number ==>", "NUMBER ==> $number")

                val numberEntity = NumberEntity(
                    id = 0L,
                    number = number.toInt(),
                    description = it
                )
                repository.insert(numberEntity)
                withContext(Main) {
                    result(numberEntity)
                }
            }
        }
    }
}