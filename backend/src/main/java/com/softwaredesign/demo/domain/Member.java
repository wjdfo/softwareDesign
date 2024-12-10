package com.softwaredesign.demo.domain;

import com.softwaredesign.demo.dto.RequestRegisterDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;
}
