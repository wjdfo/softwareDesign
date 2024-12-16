package com.softwaredesign.demo.dto;

import java.time.LocalDateTime;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
public class ReturnArticleUploadDto {
    private String message;
    private final String title;
    private Long article_id;
    private LocalDateTime time;
}
