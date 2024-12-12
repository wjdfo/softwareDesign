package com.softwaredesign.demo.controller;

import com.softwaredesign.demo.dto.*;
import com.softwaredesign.demo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/member/{member_id}")
    public ResponseEntity<ReturnChatListDto> getChat(@PathVariable("member_id") String member_id) {
        System.out.println(member_id);
        return chatService.getChat(new RequestGetChatListDto(member_id));
    }

    @PostMapping("/article/{article_id}")
    public ResponseEntity<ReturnMakeChatDto> sendChat(@PathVariable("article_id") Long article_id, @RequestBody RequestChatDto request){
        MakeChatDto makeChatDto = MakeChatDto.builder()
            .member_id(request.getMember_id())
            .article_id(article_id)
            .build();

        return chatService.makeChat(makeChatDto);
    }
}
