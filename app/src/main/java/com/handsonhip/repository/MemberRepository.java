package com.handsonhip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.handsonhip.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}