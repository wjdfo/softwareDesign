package com.softwaredesign.demo.controller;

import com.softwaredesign.demo.dto.DeleteDto;
import com.softwaredesign.demo.dto.RequestArticleDeleteDto;
import com.softwaredesign.demo.dto.RequestArticleUploadDto;
import com.softwaredesign.demo.dto.ReturnAriticleListDto;
import com.softwaredesign.demo.dto.ReturnArticleDto;
import com.softwaredesign.demo.dto.ReturnArticleUploadDto;
import com.softwaredesign.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/")
    public ResponseEntity<ReturnAriticleListDto> getArticleList() {
        return articleService.getArticleList();
    }

    @GetMapping("/{article_id}")
    public ResponseEntity<ReturnArticleDto> getArticle(@PathVariable("article_id") long article_id) {

        return articleService.getArticle(article_id);
    }

    @PutMapping("/")
    public ResponseEntity<ReturnArticleUploadDto> uploadArticle(@RequestBody RequestArticleUploadDto request) {

        return articleService.uploadArticle(request);
    }

    @PatchMapping("/{article_id}")
    public HttpStatus modifyArticle(@PathVariable("article_id") Long article_id) {

        return HttpStatus.OK;
    }

    @DeleteMapping("/{article_id}")
    public HttpStatus deleteArticle(@PathVariable("article_id") Long article_id,
        @RequestBody RequestArticleDeleteDto request) {

        DeleteDto deleteDto = DeleteDto.builder()
            .member_id(request.getMember_id())
            .article_id(article_id)
            .build();

        return articleService.deleteArticle(deleteDto);
    }
}
