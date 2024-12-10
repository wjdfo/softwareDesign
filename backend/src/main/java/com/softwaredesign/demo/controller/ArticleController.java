package com.softwaredesign.demo.controller;

import com.softwaredesign.demo.dto.ReturnAriticleListDto;
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
    public HttpStatus getArticle(@PathVariable("article_id") long article_id) {

        return HttpStatus.OK;
    }

    @PatchMapping("/{article_id}")
    public HttpStatus modifyArticle(@PathVariable("article_id") long article_id) {

        return HttpStatus.OK;
    }

    @PutMapping("/")
    public HttpStatus uploadArticle() {

        return HttpStatus.OK;
    }

    @DeleteMapping("/{article_id}")
    public HttpStatus deleteArticle(@PathVariable("article_id") long article_id) {

        return HttpStatus.OK;
    }
}
