package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
public class RequestMyPageDto {
    private String id;
    private String password;
}
