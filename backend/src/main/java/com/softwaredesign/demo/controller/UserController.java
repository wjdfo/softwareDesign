package com.softwaredesign.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class UserController {

    @PostMapping("/register")
    public HttpStatus registerMember() {

        return HttpStatus.OK;
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
