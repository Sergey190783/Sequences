import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ServiceTest {
    lateinit var service: Service

    @BeforeEach
    fun setUp() {
        service = Service()
    }

    @Test
    fun sendMessage() {
        service.sendMessage(1, 2, "Привет1")
        service.sendMessage(2, 1, "Привет2")
        assertEquals(1, service.getChats(1).count())
        assertEquals(1, service.getChats(2).count())
    }

    @Test
    fun sendMessage1() {
        service.sendMessage(1, 2, "Привет1")
        service.sendMessage(1, 3, "Привет2")
        assertEquals(2, service.getChats(1).count())
        assertEquals(1, service.getChats(2).count())
        assertEquals(1, service.getChats(3).count())
    }

    @Test
    fun sendMessage2() {
        service.sendMessage(1, 2, "Привет1")
        service.sendMessage(1, 2, "Привет2")
        assertEquals(1, service.getChats(1).count())
        assertEquals(1, service.getChats(2).count())
    }

    @Test
    fun sendMessage3() {
        service.sendMessage(1, 1, "Привет1")
        assertEquals(1, service.getChats(1).count())
    }

    @Test
    fun sendMessage4() {
        service.sendMessage(1, 2, "")
        assertEquals(0, service.getChats(1).count())
    }

    @Test
    fun deleteChat() {
        service.sendMessage(1, 2, "Привет1")
        service.deleteChat(1)
        assertEquals(0, service.getChats(1).count())
    }

    @Test
    fun deleteChat1() {
        service.sendMessage(1, 2, "Привет1")
        service.sendMessage(1, 3, "Привет2")
        service.deleteChat(3)
        assertEquals(1, service.getChats(1).count())
    }
    @Test
    fun getMessages(){
        service.sendMessage(1, 2, "Привет1")
        service.sendMessage(2, 1, "Привет2")
        service.sendMessage(1, 2, "Пока")
        service.sendMessage(2, 1, "Пока1")
        assertEquals(2, service.getMessages(1,2,2)?.count())
    }
    @Test
    fun getUnreadChatsCount(){
        service.sendMessage(1, 2, "Привет1")
        service.sendMessage(3, 2, "Привет2")
        assertEquals(2,service.getUnreadChatsCount(2))
    }
    @Test
    fun removeMessage(){
        service.sendMessage(1, 2, "Привет1")
        service.sendMessage(2, 1, "Привет2")
        service.removeMessage(1,2)
        assertEquals(1, service.getChats(1).count())
    }
    @Test
    fun removeMessage1(){
        service.sendMessage(1, 2, "Привет1")
        service.sendMessage(2, 1, "Привет2")
        service.removeMessage(1,2)
        service.removeMessage(1,3)
        assertEquals(0,service.getChats(1).count())
    }
}