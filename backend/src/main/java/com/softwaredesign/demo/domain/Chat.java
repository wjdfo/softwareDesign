package com.softwaredesign.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
public class Chat {
    @Id
    @Column(name = "message_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long message_id;

    @Column(name = "chat_id", updatable = false, nullable = false)
    private Long chat_id;

    @Column(name = "receiver_id", updatable = false, nullable = false)
    private String receiver_id;

    @Column(name = "sender_id", updatable = false, nullable = false)
    private String sender_id;

    @Column(name = "message", updatable = false, nullable = false)
    private String message;

    @Column(name = "time", updatable = false, nullable = false)
    private LocalDateTime time;
}
