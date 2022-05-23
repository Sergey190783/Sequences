import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ChatTest {

    private lateinit var chat: Chat

    @BeforeEach
    fun setUp() {
        chat = Chat(0, 1, 10)
    }

    @Test
    fun addMessage() {
        chat.addMessage(Message(0, 1, "1", 1000, false))
        chat.addMessage(Message(1, 2, "2", 1000, false))
        assertEquals(2, chat.getMessages().count())
    }

    @Test
    fun editMessage() {
        chat.addMessage(Message(0, 1, "1", 1000, false))
        chat.editMessage(1, "2")
        assertEquals("2", chat.getMessages().first().text)
    }

    @Test
    fun removeMessage() {
        chat.addMessage(Message(0, 1, "1", 1000, false))
        chat.removeMessage(1)
        assertEquals(0, chat.getMessages().count())
    }

    @Test
    fun isRead() {
        chat.addMessage(Message(0, 1, "1", 1000, false))
        assertFalse(chat.getMessages().first().read)
        chat.markMessagesAsRead(1, 1)
        assertTrue(chat.getMessages().first().read)
    }
}