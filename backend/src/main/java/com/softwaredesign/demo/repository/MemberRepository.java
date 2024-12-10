package com.softwaredesign.demo.repository;

import com.softwaredesign.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> { // 1 : entity, 2 : pk type
    boolean existsById(String id);
}
