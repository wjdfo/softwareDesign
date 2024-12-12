package com.softwaredesign.demo.dto;

import java.time.LocalDateTime;
import lombok.*;

@Data
@AllArgsConstructor
public class GetChatDto {
    private String sender_id;
    private String message;
    private LocalDateTime time;
}
