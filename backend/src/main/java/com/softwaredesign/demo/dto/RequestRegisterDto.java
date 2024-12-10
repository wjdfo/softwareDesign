package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
@Getter
public class RequestRegisterDto {
    private final String id;
    private final String password;
}
