package com.softwaredesign.demo.dto;

import java.util.Date;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ReturnArticleDto {
    private final Long id;
    private final String owner_id;
    private final String title;
    private String image;
    private String text;
    private Date time;
}
