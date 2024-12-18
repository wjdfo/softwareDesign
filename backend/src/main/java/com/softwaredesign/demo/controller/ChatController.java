package com.softwaredesign.demo.controller;

import com.softwaredesign.demo.dto.*;
import com.softwaredesign.demo.service.ChatService;
import lombok.Getter;
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
    public ResponseEntity<ReturnChatListDto> getChatList(@PathVariable("member_id") String member_id) {
        return chatService.getChatList(new RequestGetChatListDto(member_id));
    }

    @PostMapping("/article/{article_id}")
    public ResponseEntity<ReturnMakeChatDto> makeChat(@PathVariable("article_id") Long article_id, @RequestBody RequestChatDto request){
        MakeChatDto makeChatDto = MakeChatDto.builder()
            .member_id(request.getMember_id())
            .article_id(article_id)
            .build();

        return chatService.makeChat(makeChatDto);
    }

    @GetMapping("/{chat_id}")
    public ResponseEntity<ReturnGetChatDto> getChat(@PathVariable("chat_id") Long chat_id) {
        RequestGetChatDto request = RequestGetChatDto.builder()
            .chat_id(chat_id)
            .build();

        return chatService.getChat(request);
    }

    @PostMapping("/{chat_id}")
    public ResponseEntity<ReturnSendChatDto> sendChat(@PathVariable("chat_id") Long chat_id, @RequestBody RequestSendChatDto request) {
        SendChatDto sendChatDto = SendChatDto.builder()
            .chat_id(chat_id)
            .sender_id(request.getMember_id())
            .message(request.getMessage())
            .build();

        return chatService.sendChat(sendChatDto);
    }
}
