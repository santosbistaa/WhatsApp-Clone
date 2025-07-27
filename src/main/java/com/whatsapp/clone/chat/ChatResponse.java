package com.whatsapp.clone.chat;

import lombok.*;

import java.time.LocalDateTime;


// this class is a DTO which is used to send structured chat information from the backend to frontend
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatResponse {

    private String id;
    private String name;
    private long unreadCount;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private boolean isRecipientOnline;
    private String senderId;
    private String receiverId;
}
