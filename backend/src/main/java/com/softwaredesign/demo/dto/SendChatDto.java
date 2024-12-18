package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@Builder
@Getter
public class SendChatDto {
    private Long chat_id;
    private String sender_id;
    private String message;
}
