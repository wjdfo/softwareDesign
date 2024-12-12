package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
public class RequestSendChatDto {
    private String member_id;
    private String message;
}
