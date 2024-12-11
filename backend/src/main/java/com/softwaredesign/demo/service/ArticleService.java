package com.softwaredesign.demo.service;

import com.softwaredesign.demo.domain.Article;
import com.softwaredesign.demo.dto.*;
import com.softwaredesign.demo.repository.ArticleRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ResponseEntity<ReturnAriticleListDto> getArticleList() {
        List<ReturnArticleDto> articleList = new ArrayList<>();
        List<Article> result = articleRepository.findAll();

        for (Article row : result) {
            articleList.add(new ReturnArticleDto(row.getId(), row.getOwner(), row.getTitle(), row.getImage(), row.getText(), row.getTime()));
        }

        return ResponseEntity.ok().body(new ReturnAriticleListDto(articleList));
    }

    public ResponseEntity<ReturnArticleDto> getArticle(Long article_id) {
        Article result = articleRepository.findById(article_id)
            .orElseThrow();

        return ResponseEntity.ok().body(new ReturnArticleDto(result.getId(), result.getOwner(),
            result.getTitle(), result.getImage(), result.getText(), result.getTime()));
    }

    public ResponseEntity<ReturnArticleUploadDto> uploadArticle(RequestArticleUploadDto request) {
        ReturnArticleUploadDto response = new ReturnArticleUploadDto(request.getTitle());

        Article article = Article.builder()
            .owner(request.getMember_id())
            .title(request.getTitle())
            .image(request.getImage())
            .text(request.getText())
            .time(LocalDateTime.now())
            .build();

        articleRepository.save(article);
        response.setMessage(request.getTitle() + " is uploaded successfully");

        return ResponseEntity.ok().body(response);
    }
}