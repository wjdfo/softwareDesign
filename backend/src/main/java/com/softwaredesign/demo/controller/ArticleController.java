package com.softwaredesign.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController("/api/article")
public class ArticleController {

    @GetMapping("/")
    public HttpStatus getArticleList() {

        return HttpStatus.OK;
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
