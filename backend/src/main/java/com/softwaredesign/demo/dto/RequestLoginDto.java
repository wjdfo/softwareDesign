package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
public class RequestLoginDto {
    public final String id;
    public final String password;
}
