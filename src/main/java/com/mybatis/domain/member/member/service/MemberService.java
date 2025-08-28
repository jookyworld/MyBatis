package com.mybatis.domain.member.member.service;

import com.mybatis.domain.member.member.dto.Member;
import com.mybatis.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(int id) {
        return memberRepository.findById(id);
    }
}
