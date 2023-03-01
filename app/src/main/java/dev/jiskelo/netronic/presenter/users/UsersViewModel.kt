package dev.jiskelo.netronic.presenter.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jiskelo.netronic.repositories.UserRepositoryImpl
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UserRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow<StateUsers>(StateUsers.Default)
    var state :StateFlow<StateUsers> = _state.asStateFlow()

    fun getUsers(results :Int) {
        viewModelScope.launch(IO) {
            val response = async(IO, CoroutineStart.LAZY) { repository.getUsers(results) }.await()
            async(IO, CoroutineStart.LAZY) { repository.saveUserToDatabase(response) }.await()
            _state.emit(StateUsers.Success(response))
        }
    }

    fun clearState() {
        _state.value = StateUsers.Default
    }
}