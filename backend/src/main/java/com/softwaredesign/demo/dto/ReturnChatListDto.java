package com.softwaredesign.demo.dto;

import java.util.List;
import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ReturnChatListDto {
    private HttpStatus response;
    private List<ChatListDto> chat_Info_list;
}
