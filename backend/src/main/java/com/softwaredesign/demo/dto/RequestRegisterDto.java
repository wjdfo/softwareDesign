package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
public class RequestRegisterDto {
    private final String member_id;
    private final String password;
}
