package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
public class RequestMyPageDto {
    private String member_id;
    private String password;
}
