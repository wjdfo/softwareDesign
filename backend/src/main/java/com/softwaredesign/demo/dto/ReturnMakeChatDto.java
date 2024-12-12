package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnMakeChatDto {
    private Long chat_id;
    private String owner_id;
    private String guest_id;
}
