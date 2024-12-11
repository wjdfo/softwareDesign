package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@Getter
@Builder
public class ArticleModifyDto {
    private Long article_id;
    private String member_id;
    private String title;
    private String image;
    private String text;
}
