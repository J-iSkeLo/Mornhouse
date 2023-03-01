package dev.jiskelo.netronic.presenter.user.info

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jiskelo.netronic.repositories.UserRepositoryImpl
import dev.jiskelo.netronic.util.Constants
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val repository: UserRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow<StateUserInfo>(StateUserInfo.Default)
    val state :StateFlow<StateUserInfo> = _state.asStateFlow()

    fun checkArguments(arg: Bundle?) {
        val userId = arg?.getString(Constants.KEY_ID_USER)

        viewModelScope.launch(IO) {
            if (userId == null) {
                _state.emit(StateUserInfo.Error("User id is NULL"))
            } else {
                getUser(userId)
            }
        }

    }

    private suspend fun getUser(id :String) {
        val userEntity = repository.getUserById(id)

        if (userEntity == null) {
            _state.emit(StateUserInfo.Error("Unknown error, please try again later"))
        } else {
            _state.emit(StateUserInfo.Success(userEntity))
        }
    }
}