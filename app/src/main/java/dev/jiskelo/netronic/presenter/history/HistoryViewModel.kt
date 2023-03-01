package dev.jiskelo.netronic.presenter.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jiskelo.netronic.data.entity.UserEntity
import dev.jiskelo.netronic.repositories.UserRepositoryImpl
import dev.jiskelo.netronic.structs.User
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: UserRepositoryImpl
) :ViewModel() {

    private val _users = MutableStateFlow<List<UserEntity>>(emptyList())
    val users : StateFlow<List<UserEntity>> = _users.asStateFlow()

    init {
        viewModelScope.launch(IO) {
            repository.getHistoryUsers().collect { usersEntity ->
                _users.value = usersEntity.sortedBy { it.createAt }.reversed()
            }
        }
    }

}