
class Service {
    private val chats: MutableList<Chat> = mutableListOf()
    private var currentChatId = 0
    private fun getId(): Int {
        currentChatId++
        return currentChatId
    }

    fun getUnreadChatsCount(userId: Int):Int {
        var count = 0
        chats.filter { it.userId == userId || it.friendId == userId }.forEach {
            if (!it.isRead())
                count++
        }
        return count
    }

    fun getChats(userId: Int): Sequence<Chat> {
        return chats.filter { it.userId == userId || it.friendId == userId }.asSequence()
    }

    fun getMessages(chatId: Int, messageId: Int, number: Int): Sequence<Message>? {
        val chat = chats.find { it.chatId == chatId } ?: return null
        chat.markMessagesAsRead(messageId, number)
        val index = chat.indexOfMessage(messageId)
        return chat.getMessages(index, number)
    }

    fun removeMessage(chatId: Int, messageId: Int) {
        val chat = chats.find { it.chatId == chatId }
        chat?.removeMessage(messageId)
        if (chat?.isEmptyChat() == true) {
            chats.remove(chat)
        }
    }

    fun sendMessage(userId: Int, friendId: Int, message: String) {
        if(message.isEmpty()){
            return
        }
        var chat =
            chats.find { (it.friendId == friendId && it.userId == userId) || (it.userId == friendId && it.friendId == userId) }
        if (chat == null) {
            chat = Chat(userId, friendId, getId())
            chats.add(chat)
        }
        chat.addMessage(Message(userId,getId(),message,1L,false))
    }

    fun deleteChat(chatId: Int) {
        chats.removeIf { it.chatId == chatId }
    }

}