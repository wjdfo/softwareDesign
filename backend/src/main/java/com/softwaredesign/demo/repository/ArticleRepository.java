package com.softwaredesign.demo.repository;

import com.softwaredesign.demo.domain.Article;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Transactional
    @Modifying
    @Query("delete from Article a where a.owner_id = :member_id and a.article_id = :article_id")
    int deleteByArticleIdAndMemberId(@Param("member_id") String member_id, @Param("article_id") Long article_id);
}

