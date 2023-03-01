package dev.jiskelo.netronic.presenter.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private val _splashState = MutableStateFlow<SplashState>(SplashState.Empty)
    val splashState: StateFlow<SplashState> = _splashState.asStateFlow()

    init {
        viewModelScope.launch(IO) {
            _splashState.value = SplashState.Loading
            delay(2000)
            _splashState.value = SplashState.Success
        }
    }

}