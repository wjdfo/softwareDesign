package com.softwaredesign.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@RequiredArgsConstructor
public class Chat {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public final Long chat_id;

    @Column(name = "Send_ID", nullable = false)
    public final String sender;

    @Column(name = "Receive_ID", nullable = false)
    public final String receiver;

    @Column(name = "Article_ID", nullable = false)
    public final String article_id;

    @Column(name = "message")
    public final String message;

    @Column(name = "time", nullable = false)
    public final String time;
}
