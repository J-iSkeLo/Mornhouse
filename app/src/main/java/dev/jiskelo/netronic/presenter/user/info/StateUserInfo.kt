package dev.jiskelo.netronic.presenter.user.info

import dev.jiskelo.netronic.data.entity.UserEntity

sealed class StateUserInfo {
    object Default : StateUserInfo()
    data class Success(val userEntity: UserEntity) : StateUserInfo()
    data class Error(val message :String) : StateUserInfo()
}