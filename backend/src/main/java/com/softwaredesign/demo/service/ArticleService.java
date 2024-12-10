package com.softwaredesign.demo.service;

import com.softwaredesign.demo.domain.Article;
import com.softwaredesign.demo.dto.ReturnAriticleListDto;
import com.softwaredesign.demo.dto.ReturnArticleDto;
import com.softwaredesign.demo.repository.ArticleRepository;
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
}
