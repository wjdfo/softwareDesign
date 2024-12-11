package com.softwaredesign.demo.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Article_id")
    private Long id;

    @Column(name = "Owner_ID", nullable = false)
    private String owner;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "Image")
    private String image;

    @Column(name = "Text")
    private String text;

    @Column(name = "Time", nullable = false)
    private LocalDateTime time;
}
