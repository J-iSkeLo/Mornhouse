package dev.jiskelo.netronic.structs

@kotlinx.serialization.Serializable
data class Info(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
)