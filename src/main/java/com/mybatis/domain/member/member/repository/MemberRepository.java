package com.mybatis.domain.member.member.repository;

import com.mybatis.domain.member.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRepository {

    List<Member> findAll();

    Member findById(int id);
}
