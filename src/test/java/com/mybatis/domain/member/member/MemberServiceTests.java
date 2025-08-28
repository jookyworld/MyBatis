package com.mybatis.domain.member.member;

import com.mybatis.domain.member.member.dto.Member;
import com.mybatis.domain.member.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("Test")
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원 다건 조회")
    void t1() {
        List<Member> members = memberService.findAll();

        assertThat(members).hasSize(2);
    }

    @Test
    @DisplayName("회원 단건 조회 - ID로 조회")
    void t2() {
        Member member = memberService.findById(1);

        assertThat(member.getUsername()).isEqualTo("user1");
        assertThat(member.getName()).isEqualTo("유저1");
        assertThat(member.getEmail()).isEqualTo("user1@test.com");
    }

    @Test
    @DisplayName("회원 단건 조회 - Username으로 조회")
    void t3() {
        Member member = memberService.findByUsername("user2");

        assertThat(member.getName()).isEqualTo("유저2");
        assertThat(member.getEmail()).isEqualTo("user2@test.com");
    }

    @Test
    @DisplayName("회원 생성")
    void t4() {
        int id = memberService.create("user3", "{noop}1234", "유저3", "user3@test.com");

        Member member = memberService.findById(id);

        assertThat(member.getUsername()).isEqualTo("user3");
        assertThat(member.getPassword()).isEqualTo("{noop}1234");
        assertThat(member.getName()).isEqualTo("유저3");
        assertThat(member.getEmail()).isEqualTo("user3@test.com");
    }
}
