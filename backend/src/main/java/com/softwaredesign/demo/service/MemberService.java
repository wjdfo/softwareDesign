package com.softwaredesign.demo.service;

import com.softwaredesign.demo.domain.Member;
import com.softwaredesign.demo.dto.*;
import com.softwaredesign.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public ReturnRegisterDto register(RequestRegisterDto registerInfo) {
        ReturnRegisterDto returnRegisterDto = new ReturnRegisterDto(HttpStatus.OK, registerInfo.getId());

        if (memberRepository.existsById(registerInfo.getId())) {
            returnRegisterDto.setMessage(HttpStatus.BAD_REQUEST);

            return returnRegisterDto;
        }

        Member member = Member.builder()
            .id(registerInfo.getId())
            .password(registerInfo.getPassword())
            .build();
        memberRepository.save(member);
        return returnRegisterDto;
    }
}
