package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
@Setter
public class ReturnMyPageDto {
    private final String id;
    private String message;
}
