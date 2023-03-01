package dev.jiskelo.netronic.presenter.splash

sealed class SplashState {
    object Empty : SplashState()
    object Loading : SplashState()
    object Success : SplashState()
}