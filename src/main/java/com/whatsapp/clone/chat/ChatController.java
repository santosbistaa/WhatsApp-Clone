package com.whatsapp.clone.chat;

import com.whatsapp.clone.common.StringResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
This is a rest controller that exposes the HTTP endpoints to:
- create a chat between two users and
- gets all chats involving the current user
*/
@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
@Tag(name = "Chat")
//@Tag --> this is used to organize and document our API endpoints in Swagger UI
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<StringResponse> createChat(
            @RequestParam(name = "sender-id") String senderId,
            @RequestParam(name = "receiver-id") String receiverId
    ) {
        final String chatId = chatService.createChat(senderId, receiverId);
        StringResponse response = StringResponse.builder()
                .response(chatId)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>>  getChatsByReceiver (Authentication authentication) {
        return ResponseEntity.ok(chatService.getChatByReceiverId(authentication));
    }
}
