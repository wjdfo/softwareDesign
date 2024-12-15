package com.softwaredesign.demo.dto;

import java.time.LocalDateTime;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class ReturnArticleDto {
    private final Long id;
    private final String owner_id;
    private final String title;
    private String image;
    private String text;
    private LocalDateTime time;
}
