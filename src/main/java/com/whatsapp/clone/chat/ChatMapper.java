package com.whatsapp.clone.chat;

import org.springframework.stereotype.Service;


// this class is responsible for converting a chat entity into a DTO called chat response
@Service
public class ChatMapper {
    public ChatResponse toChatResponse(Chat chat, String senderId) {
        return ChatResponse.builder()
                .id(chat.getId())
                .name(chat.getChatName(senderId))
                .unreadCount(chat.getUnreadMessages(senderId))
                .lastMessage(chat.getLastMessage())
                .isRecipientOnline(chat.getRecipient().isUserOnline())
                .senderId(chat.getSender().getId())
                .receiverId(chat.getRecipient().getId())
                .lastMessageTime(chat.getLastMessageTime())
                .build();
    }
}
