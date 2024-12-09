package com.softwaredesign.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@RequiredArgsConstructor
public class Chat {
    @Id
    @Column(name = "Chat_ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public final Long chat_id;

    @Column(name = "Send_ID")
    public final String sender;

    @Column(name = "Receive_ID")
    public final String receiver;

    @Column(name = "Article_ID")
    public final String article_id;

    @Column(name = "message")
    public final String message;

    @Column(name = "time")
    public final String time;
}
