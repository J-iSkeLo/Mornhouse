package dev.jiskelo.netronic.structs

@kotlinx.serialization.Serializable
data class Name(
    val first: String,
    val last: String,
    val title: String
) {
    fun getName() :String {
        return "$title $first $last"
    }
}