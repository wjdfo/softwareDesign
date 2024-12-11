package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
public class RequestArticleModifyDto {
    private String member_id;
    private String title;
    private String image;
    private String text;
}
