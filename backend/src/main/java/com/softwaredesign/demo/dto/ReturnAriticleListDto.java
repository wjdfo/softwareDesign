package com.softwaredesign.demo.dto;

import com.softwaredesign.demo.domain.Article;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ReturnAriticleListDto {
    private List<ReturnArticleDto> article_list;
}
