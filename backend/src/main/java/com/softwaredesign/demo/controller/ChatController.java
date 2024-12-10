package com.softwaredesign.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ChatController {
    @PostMapping("/{article_id}/chat")
    public HttpStatus chat(@PathVariable("article_id") long article_id){

        return HttpStatus.OK;
    }
}
