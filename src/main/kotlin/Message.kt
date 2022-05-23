data class Message(
    val senderId: Int,
    val id: Int,
    var text: String,
    var date: Long,
    var read: Boolean
)