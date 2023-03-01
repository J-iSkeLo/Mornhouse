package dev.jiskelo.netronic.structs

@kotlinx.serialization.Serializable
data class Timezone(
    val description: String,
    val offset: String
)