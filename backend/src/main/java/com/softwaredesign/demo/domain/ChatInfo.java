package com.softwaredesign.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatInfo {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long chat_id;

    @Column(name = "guest_id", nullable = false)
    public String guest_id;

    @Column(name = "owner_id", nullable = false)
    public String owner_id;

    @Column(name = "Article_ID", nullable = false)
    public Long article_id;
}
