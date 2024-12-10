package com.softwaredesign.demo.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
@Setter
public class ReturnLoginDto {
    private String message;
    private final String id;
}
