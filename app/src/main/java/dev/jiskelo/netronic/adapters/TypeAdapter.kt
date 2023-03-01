package dev.jiskelo.netronic.adapters

sealed class TypeAdapter {
    object UserAdapter :TypeAdapter()
    object UserEntityAdapter :TypeAdapter()
}
