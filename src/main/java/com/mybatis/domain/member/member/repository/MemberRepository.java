package com.mybatis.domain.member.member.repository;

import com.mybatis.domain.member.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberRepository {

    @Select("""
            SELECT * FROM member
            """)
    List<Member> findAll();
}
