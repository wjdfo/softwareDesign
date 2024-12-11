package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
public class RequestArticleUploadDto {
    private String member_id;
    private String title;
    private String image;
    private String text;
}
