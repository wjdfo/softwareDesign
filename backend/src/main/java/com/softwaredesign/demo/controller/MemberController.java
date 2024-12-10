package com.softwaredesign.demo.controller;

import com.softwaredesign.demo.dto.RequestRegisterDto;
import com.softwaredesign.demo.dto.ReturnRegisterDto;
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

    @PostMapping("/register")
    public HttpStatus registerMember(@RequestBody RequestRegisterDto request) {

        return memberService.register(request).getMessage();
    }

    @PostMapping("/login")
    public HttpStatus loginMember() {

        return HttpStatus.OK;
    }

    @GetMapping("/")
    public HttpStatus myPage() {

        return HttpStatus.OK;
    }
}
