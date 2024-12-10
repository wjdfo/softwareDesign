package com.softwaredesign.demo.domain;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Article_id")
    private final Long id;

    @Column(name = "Owner_ID", nullable = false)
    private final String owner;

    @Column(name = "title", nullable = false)
    private final String title;

    @Column(name = "Image")
    private String image;

    @Column(name = "Text")
    private String text;

    @Column(name = "Time", nullable = false)
    private Date time;
}
