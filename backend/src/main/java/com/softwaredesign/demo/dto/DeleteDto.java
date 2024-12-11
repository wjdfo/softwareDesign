package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@Builder
@Getter
public class DeleteDto {
    private String member_id;
    private Long article_id;
}
