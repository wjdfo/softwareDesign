package com.softwaredesign.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@RequiredArgsConstructor
@Getter
public class Member {
    @Id
    @Column(name = "id", updatable = false)
    private final String id;

    @Column(name = "password")
    private final String password;

    @Column(name = "nickname")
    private final String nickname;
}
