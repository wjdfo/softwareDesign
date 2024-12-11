package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
public class ReturnArticleUploadDto {
    private String message;
    private final String title;
}
