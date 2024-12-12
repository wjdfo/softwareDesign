package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class ReturnSendChatDto {
    private String sender_id;
    private String receiver_id;
    private String message;
}
