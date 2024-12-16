package com.softwaredesign.demo.controller;

import com.softwaredesign.demo.dto.*;
import com.softwaredesign.demo.service.*;
import com.softwaredesign.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final ArticleService articleService;

    @PostMapping("/register")
    public ResponseEntity<ReturnRegisterDto> registerMember(@RequestBody RequestRegisterDto request) {
        return memberService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<ReturnLoginDto> loginMember(@RequestBody RequestLoginDto request) {
        return memberService.login(request);
    }

    @GetMapping("/")
    public ResponseEntity<ReturnAriticleListDto> myPage(@RequestBody RequestMyPageDto request) {
        return articleService.getArticleListByOwnerId(request);
    }

    @PatchMapping("/")
    public ResponseEntity<ReturnMyPageDto> modifyMyPage(@RequestBody RequestMyPageDto request) {
        return memberService.modifyPassword(request);
    }

}
