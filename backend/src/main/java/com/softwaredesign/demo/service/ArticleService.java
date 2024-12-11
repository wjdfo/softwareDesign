package com.softwaredesign.demo.service;

import com.softwaredesign.demo.domain.Article;
import com.softwaredesign.demo.dto.*;
import com.softwaredesign.demo.repository.ArticleRepository;
import java.time.LocalDateTime;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    // 게시글 리스트
    public ResponseEntity<ReturnAriticleListDto> getArticleList() {
        List<ReturnArticleDto> articleList = new ArrayList<>();
        List<Article> result = articleRepository.findAll();

        for (Article row : result) {
            articleList.add(new ReturnArticleDto(row.getArticle_id(), row.getOwner_id(), row.getTitle(), row.getImage(), row.getText(), row.getTime()));
        }

        return ResponseEntity.ok().body(new ReturnAriticleListDto(articleList));
    }

    // 특정 게시글 조회
    public ResponseEntity<ReturnArticleDto> getArticle(Long article_id) {
        Article result = articleRepository.findById(article_id)
            .orElseThrow();

        return ResponseEntity.ok().body(new ReturnArticleDto(result.getArticle_id(), result.getOwner_id(),
            result.getTitle(), result.getImage(), result.getText(), result.getTime()));
    }

    // 게시글 업로드
    public ResponseEntity<ReturnArticleUploadDto> uploadArticle(RequestArticleUploadDto request) {
        ReturnArticleUploadDto response = new ReturnArticleUploadDto(request.getTitle());

        Article article = Article.builder()
            .owner_id(request.getMember_id())
            .title(request.getTitle())
            .image(request.getImage())
            .text(request.getText())
            .time(LocalDateTime.now())
            .build();

        articleRepository.save(article);
        response.setMessage(request.getTitle() + " is uploaded successfully");

        return ResponseEntity.ok().body(response);
    }

    // 게시글 수정
    public HttpStatus modifyArticle(ArticleModifyDto request) {
        if (articleRepository.updateArticleByMemberIdAndArticleId(
            request.getTitle(),
            request.getImage(),
            request.getText(),
            request.getMember_id(),
            request.getArticle_id()
        ) > 0 ) {
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }

    // 게시글 삭제
    public HttpStatus deleteArticle(ArticleDeleteDto request) {
        if (articleRepository.deleteByArticleIdAndMemberId(
            request.getMember_id(),
            request.getArticle_id()) > 0) {
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}