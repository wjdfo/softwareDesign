package com.softwaredesign.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@RequiredArgsConstructor
@Getter
public class Member {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final String id;

    @Column(name = "password")
    @NonNull
    private final String password;

    @Column(name = "nickname")
    @NonNull
    private final String nickname;
}
