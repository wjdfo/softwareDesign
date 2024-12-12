package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@Builder
public class RequestGetChatDto {
    private Long chat_id;
}
