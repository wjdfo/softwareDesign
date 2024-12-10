package com.softwaredesign.demo.controller;

import com.softwaredesign.demo.dto.RequestLoginDto;
import com.softwaredesign.demo.dto.RequestRegisterDto;
import com.softwaredesign.demo.dto.ReturnLoginDto;
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
    public ResponseEntity<ReturnRegisterDto> registerMember(@RequestBody RequestRegisterDto request) {
        return memberService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<ReturnLoginDto> loginMember(@RequestBody RequestLoginDto request) {
        return memberService.login(request);
    }

    @GetMapping("/")
    public HttpStatus myPage() {

        return HttpStatus.OK;
    }
}
