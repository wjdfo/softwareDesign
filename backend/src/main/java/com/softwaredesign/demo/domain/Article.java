package com.softwaredesign.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Article_ID", updatable = false)
    private final String id;

    @Column(name = "Owner_ID")
    private final String owner;

    @Column(name = "title")
    private final String title;

    @Column(name = "Image")
    private String image;

    @Column(name = "Text")
    private String text;

    @Column(name = "Time")
    private String time;
}
