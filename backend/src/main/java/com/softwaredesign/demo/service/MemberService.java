package com.softwaredesign.demo.service;

import com.softwaredesign.demo.domain.Member;
import com.softwaredesign.demo.dto.*;
import com.softwaredesign.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public ResponseEntity<ReturnRegisterDto> register(RequestRegisterDto request) {
        ReturnRegisterDto returnRegisterDto = new ReturnRegisterDto(HttpStatus.OK, request.getId());

        // 이미 존재하는 ID
        if (memberRepository.existsById(request.getId())) {
            returnRegisterDto.setMessage(HttpStatus.BAD_REQUEST);

            return new ResponseEntity<ReturnRegisterDto>(returnRegisterDto, HttpStatus.BAD_REQUEST);
        }

        // ID 생성
        Member member = Member.builder()
            .id(request.getId())
            .password(request.getPassword())
            .build();
        memberRepository.save(member);
        return new ResponseEntity<ReturnRegisterDto>(returnRegisterDto, HttpStatus.CREATED);
    }

    public ResponseEntity<ReturnLoginDto> login(RequestLoginDto request) {
        ReturnLoginDto returnLoginDto = new ReturnLoginDto(request.getId());

        // ID 존재할 때
        if (memberRepository.existsById(request.getId())) {
            // Log in 성공
            if (memberRepository.existsByIdAndPassword(request.getId(), request.getPassword())) {
                returnLoginDto.setMessage("Log in success !");

                return new ResponseEntity<ReturnLoginDto>(returnLoginDto, HttpStatus.OK);
            }

            // 비밀번호 불일치
            returnLoginDto.setMessage("password error");
            return new ResponseEntity<ReturnLoginDto>(returnLoginDto, HttpStatus.BAD_REQUEST);
        }

        // ID 불일치
        else {returnLoginDto.setMessage(request.getId() + " does not exist.");}
            return new ResponseEntity<ReturnLoginDto>(returnLoginDto, HttpStatus.BAD_REQUEST);
    }
}
