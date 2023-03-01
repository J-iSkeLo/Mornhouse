package dev.jiskelo.netronic.structs

@kotlinx.serialization.Serializable
data class Street(
    val name: String,
    val number: Int
)