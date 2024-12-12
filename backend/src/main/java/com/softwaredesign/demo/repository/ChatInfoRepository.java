package com.softwaredesign.demo.repository;

import com.softwaredesign.demo.domain.ChatInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatInfoRepository extends JpaRepository<ChatInfo, Long> {

}
