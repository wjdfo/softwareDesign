package com.softwaredesign.demo.repository;

import com.softwaredesign.demo.domain.*;
import com.softwaredesign.demo.dto.GetChatDto;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("select new com.softwaredesign.demo.dto.GetChatDto(c.sender_id, c.message, c.time) "
        + "from Chat c where c.chat_id = :chat_id order by c.time asc")
    List<GetChatDto> findAllByChatId(@Param("chat_id") Long chat_id);
}
