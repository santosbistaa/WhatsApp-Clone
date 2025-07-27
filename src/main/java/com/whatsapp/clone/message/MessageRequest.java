package com.whatsapp.clone.message;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

// This class is a DTO that represents the structure of the message data being sent
// from the client to the server --> create input model for creating/sending messages
public class MessageRequest {

    private String content;
    private String senderId;
    private String receiverId;
    private MessageType type;
    private String chatId;
}
