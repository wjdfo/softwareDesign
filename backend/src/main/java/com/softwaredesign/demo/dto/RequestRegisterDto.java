package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
public class RequestRegisterDto {
    private final String id;
    private final String password;
}
