package dev.jiskelo.netronic.structs

@kotlinx.serialization.Serializable
data class Registered(
    val age: Int,
    val date: String
)