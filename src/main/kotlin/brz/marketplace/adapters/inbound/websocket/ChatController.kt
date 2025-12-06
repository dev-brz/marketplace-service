package brz.marketplace.adapters.inbound.websocket

import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
private class ChatController {
    private val logger = LoggerFactory.getLogger(ChatController::class.java)

    @MessageMapping("/chat")
    @SendTo("/topic/chat/broadcast")
    fun broadcastToAll(@Payload message: ChatMessage): ChatMessage {
        logger.info("Received message: {}", message.content)
        return ChatMessage(content = "Broadcast: ${message.content}")
    }

    data class ChatMessage(val content: String)
}
