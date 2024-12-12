package com.softwaredesign.demo.controller;

import com.softwaredesign.demo.dto.*;
import com.softwaredesign.demo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/")
    public void getChat(@RequestBody RequestChatDto request) {

    }

    @PostMapping("/{article_id}/chat")
    public ResponseEntity<ReturnMakeChatDto> sendChat(@PathVariable("article_id") Long article_id, @RequestBody RequestChatDto request){
        MakeChatDto makeChatDto = MakeChatDto.builder()
            .member_id(request.getMember_id())
            .article_id(article_id)
            .build();

        return chatService.makeChat(makeChatDto);
    }
}
