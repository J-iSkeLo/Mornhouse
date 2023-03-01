package dev.jiskelo.netronic.structs

@kotlinx.serialization.Serializable
data class Id(
    val name: String,
    val value: String
)