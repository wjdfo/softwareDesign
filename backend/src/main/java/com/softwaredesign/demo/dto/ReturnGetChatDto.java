package com.softwaredesign.demo.dto;

import java.util.List;
import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ReturnGetChatDto {
    private List<GetChatDto> chat_list;
}
