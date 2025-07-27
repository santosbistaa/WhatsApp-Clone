package com.whatsapp.clone.user;

import com.whatsapp.clone.chat.Chat;
import com.whatsapp.clone.common.BaseAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
// find a user by email
@NamedQuery(name = UserConstants.FIND_USER_BY_EMAIL,
            query = "SELECT u FROM User u WHERE u.email = :email")

// get all users except the current one
@NamedQuery(name = UserConstants.FIND_ALL_USERS_EXCEPT_SELF,
            query = "SELECT u FROM User u WHERE u.id != :publicId")

// find a user by ID (from KeyCloak)
@NamedQuery(name = UserConstants.FIND_USER_BY_PUBLIC_ID,
            query = "SELECT u FROM  User u WHERE  u.id = :publicId")
public class User extends BaseAuditingEntity {

    private static final int LAST_ACTIVE_INTERVAL = 5;
    // since this id is not self generated.We get this id from KeyCloak in UUID format so String
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime lastSeen;

    // chats where the user is sender
    @OneToMany(mappedBy = "sender")
    private List<Chat> chatASSender;


    // chats where the user is recipient
    @OneToMany(mappedBy = "recipient")
    private List<Chat> chatASRecipient;

    // checks if the user was active in last 5 minutes
    @Transient
    public boolean isUserOnline(){
        return lastSeen !=null && lastSeen.isAfter(LocalDateTime.now().minusMinutes(LAST_ACTIVE_INTERVAL));
    }
}
