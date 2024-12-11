package com.softwaredesign.demo.repository;

import com.softwaredesign.demo.domain.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> { // 1 : entity, 2 : pk type
    boolean existsById(String id);

    boolean existsByIdAndPassword(String id, String password);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.password = :newPassword WHERE m.Id = :Id")
    int updatePassword(@Param("Id") String Id, @Param("newPassword") String newPassword);
}
