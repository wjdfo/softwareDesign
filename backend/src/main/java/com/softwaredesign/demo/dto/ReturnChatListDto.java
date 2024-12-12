package com.softwaredesign.demo.dto;

import com.softwaredesign.demo.domain.ChatInfo;
import java.util.List;
import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ReturnChatListDto {
    private HttpStatus response;
    private List<ChatList> chat_Info_list;
}
