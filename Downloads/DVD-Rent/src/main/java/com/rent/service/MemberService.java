package com.rent.service;

import com.rent.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService extends AbstractService<Member> {
    public MemberService(JpaRepository<Member, Long> repository) {
        super(repository);
    }
}
