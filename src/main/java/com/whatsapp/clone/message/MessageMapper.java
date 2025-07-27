package com.whatsapp.clone.message;

import com.whatsapp.clone.file.FileUtils;
import org.springframework.stereotype.Service;

@Service
// This is used to convert a message entity into a message response DTO, which is then
// sent back to the client.
public class MessageMapper {
    public MessageResponse toMessageResponse(Message message) {
        return MessageResponse.builder()
                .id(message.getId())
                .content(message.getContent())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .type(message.getType())
                .state(message.getState())
                .createdAt(message.getCreatedDate())
                .media(FileUtils.readFileFromLocation(message.getMediaFilePath()))
                .build();
    }
}
