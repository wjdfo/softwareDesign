package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
public class RequestLoginDto {
    public final String member_id;
    public final String password;
}
