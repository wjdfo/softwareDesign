package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class ChatListDto {
    private Long chat_id;
    private Long article_id;
    private String article_title;
}
