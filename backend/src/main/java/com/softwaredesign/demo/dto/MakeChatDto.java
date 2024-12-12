package com.softwaredesign.demo.dto;

import lombok.*;

@Data
@Getter
@Builder
public class MakeChatDto {
    private String member_id;
    private Long article_id;
}
