package dev.jiskelo.netronic.structs



@kotlinx.serialization.Serializable
data class Coordinates(
    val latitude: String,
    val longitude: String
)