package com.softwaredesign.demo.repository;

import com.softwaredesign.demo.domain.ChatInfo;
import com.softwaredesign.demo.dto.ChatList;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatInfoRepository extends JpaRepository<ChatInfo, Long> {

    @Query("select new com.softwaredesign.demo.dto.ChatList(c.chat_id, a.article_id, a.title) "
        + "from Article a, ChatInfo c "
        + "where a.article_id = c.article_id and (c.guest_id = :member_id or c.owner_id = :member_id)")
    List<ChatList> findChatInfoByMemberId(@Param("member_id") String member_id);
}
