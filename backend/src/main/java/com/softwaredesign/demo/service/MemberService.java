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
        ReturnRegisterDto returnRegisterDto = new ReturnRegisterDto(HttpStatus.OK, request.getMember_id());

        // 이미 존재하는 ID
        if (memberRepository.existsById(request.getMember_id())) {
            returnRegisterDto.setMessage(HttpStatus.BAD_REQUEST);

            return new ResponseEntity<ReturnRegisterDto>(returnRegisterDto, HttpStatus.BAD_REQUEST);
        }

        // ID 생성
        Member member = Member.builder()
            .id(request.getMember_id())
            .password(request.getPassword())
            .build();
        memberRepository.save(member);
        return new ResponseEntity<ReturnRegisterDto>(returnRegisterDto, HttpStatus.CREATED);
    }

    public ResponseEntity<ReturnLoginDto> login(RequestLoginDto request) {
        ReturnLoginDto returnLoginDto = new ReturnLoginDto(request.getMember_id());

        // ID 존재할 때
        if (memberRepository.existsById(request.getMember_id())) {
            // Log in 성공
            if (memberRepository.existsByIdAndPassword(request.getMember_id(), request.getPassword())) {
                returnLoginDto.setMessage("Log in success !");

                return new ResponseEntity<ReturnLoginDto>(returnLoginDto, HttpStatus.OK);
            }

            // 비밀번호 불일치
            else {returnLoginDto.setMessage("password error");}
            return new ResponseEntity<ReturnLoginDto>(returnLoginDto, HttpStatus.BAD_REQUEST);
        }

        // ID 불일치
        else {returnLoginDto.setMessage(request.getMember_id() + " does not exist.");}
            return new ResponseEntity<ReturnLoginDto>(returnLoginDto, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ReturnMyPageDto> modifyPassword(RequestMyPageDto request) {
        ReturnMyPageDto returnMyPageDto = new ReturnMyPageDto(request.getMember_id());
        HttpStatus code = HttpStatus.BAD_REQUEST;

        // 새로운 비밀번호가 현재 비밀번호와 같은 경우
        if (memberRepository.existsByIdAndPassword(request.getMember_id(), request.getPassword())) {
            returnMyPageDto.setMessage("new password must be different with current one.");
        }

        else {
            if (memberRepository.updatePassword(request.getMember_id(), request.getPassword()) > 0) {
                code = HttpStatus.OK;
                returnMyPageDto.setMessage("password is changed.");
            }
        }

        return new ResponseEntity<ReturnMyPageDto>(returnMyPageDto, code);
    }
}
