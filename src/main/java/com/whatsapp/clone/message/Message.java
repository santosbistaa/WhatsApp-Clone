package com.whatsapp.clone.message;

import com.whatsapp.clone.chat.Chat;
import com.whatsapp.clone.common.BaseAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")

// Get messages in a specific chat
@NamedQuery(name = MessageConstants.FIND_MESSAGES_BY_CHAT_ID,
            query = "SELECT m FROM Message m WHERE m.chat.id = :chatId ORDER BY m.createdDate")

// mark all messages in the chat as seen
@NamedQuery(name = MessageConstants.SET_MESSAGES_TO_SEEN_BY_CHAT,
            query = "UPDATE Message SET state = :newState WHERE chat.id = :chatId")
public class Message extends BaseAuditingEntity {

    @Id
    @SequenceGenerator(name = "msg_seq", sequenceName = "msg_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg_seq")
    private Long id;

    // text of attachment info
    @Column(columnDefinition = "TEXT")
    private String content;

    // state sent or seen
    @Enumerated(EnumType.STRING)
    private MessageState state;

    // type text,image,audio,video
    @Enumerated(EnumType.STRING)
    private MessageType type;

    // link to the chat it belongs to
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    // sender Id
    @Column(name = "sender_id", nullable = false)
    private String senderId;

    // receiver Id
    @Column(name = "receiver_id", nullable = false)
    private String receiverId;

    private String mediaFilePath;
}
