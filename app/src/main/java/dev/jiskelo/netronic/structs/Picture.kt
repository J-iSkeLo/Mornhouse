package dev.jiskelo.netronic.structs

@kotlinx.serialization.Serializable
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)