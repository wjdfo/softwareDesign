package com.softwaredesign.demo.service;

import com.softwaredesign.demo.domain.*;
import com.softwaredesign.demo.dto.*;
import com.softwaredesign.demo.repository.*;
import java.sql.SQLDataException;
import lombok.*;
import org.apache.coyote.Response;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatInfoRepository chatInfoRepository;
    private final ArticleRepository articleRepository;

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
