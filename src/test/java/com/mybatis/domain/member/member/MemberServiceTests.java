package com.mybatis.domain.member.member;

import com.mybatis.domain.member.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("Test")
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;

    @Test
    void t1() {

    }
}
