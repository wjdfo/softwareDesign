package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestArticleUploadDto {
    private final String member_id;
    private final String title;
    private String image;
    private String text;
}
