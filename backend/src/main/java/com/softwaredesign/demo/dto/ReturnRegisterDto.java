package com.softwaredesign.demo.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Setter
@Getter
public class ReturnRegisterDto {
    private HttpStatus message;
    private final String member_id;
}
