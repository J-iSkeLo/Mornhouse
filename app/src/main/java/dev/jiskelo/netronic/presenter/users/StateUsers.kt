package dev.jiskelo.netronic.presenter.users

import dev.jiskelo.netronic.structs.User

sealed class StateUsers {
    object Default :StateUsers()
    data class Success(val users :List<User>) :StateUsers()
    data class Error(val message :String) :StateUsers()
}