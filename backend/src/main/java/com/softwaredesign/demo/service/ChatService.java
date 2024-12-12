package com.softwaredesign.demo.service;

import com.softwaredesign.demo.domain.*;
import com.softwaredesign.demo.dto.*;
import com.softwaredesign.demo.repository.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatInfoRepository chatInfoRepository;
    private final ArticleRepository articleRepository;

    public ResponseEntity<ReturnChatListDto> getChat(RequestGetChatListDto request) {
        ReturnChatListDto response = ReturnChatListDto.builder()
            .response(HttpStatus.OK)
            .chat_Info_list(chatInfoRepository.findChatInfoByMemberId(request.getMember_id()))
            .build();

        for (ChatList temp : response.getChat_Info_list()) {
            System.out.println(temp.getChat_id() + " " + temp.getArticle_id() + " " + temp.getArticle_title());
        }

        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ReturnMakeChatDto> makeChat(MakeChatDto request) {
        try {
            String owner = articleRepository.findOwnerByArticleId(request.getArticle_id());

            ChatInfo newChatInfo = ChatInfo.builder()
                .guest_id(request.getMember_id())
                .owner_id(owner)
                .article_id(request.getArticle_id())
                .build();

            ChatInfo result = chatInfoRepository.save(newChatInfo);

            ReturnMakeChatDto response = ReturnMakeChatDto.builder()
                .chat_id(result.getChat_id())
                .owner_id(result.getOwner_id())
                .guest_id(result.getGuest_id())
                .build();

            return ResponseEntity.ok().body(response);
            } catch (EmptyResultDataAccessException e) {
                throw new RuntimeException(e);
        }
    }
}
