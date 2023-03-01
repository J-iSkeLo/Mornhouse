package dev.jiskelo.netronic.structs

@kotlinx.serialization.Serializable
data class Dob(
    val age: Int,
    val date: String
)