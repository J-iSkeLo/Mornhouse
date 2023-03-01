package dev.jiskelo.netronic.structs

@kotlinx.serialization.Serializable
data class Response(
    val info: Info,
    val results: List<User>
)