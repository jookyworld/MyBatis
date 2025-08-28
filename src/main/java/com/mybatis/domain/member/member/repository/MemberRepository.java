package com.mybatis.domain.member.member.repository;

import com.mybatis.domain.member.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRepository {

    List<Member> findAll();

    Member findById(int id);

    Member findByUsername(String username);

    int create(String username, String password, String name, String email);

    int lastInsertId();

    void deleteById(int id);

    void update(int id, String username, String password, String name, String email);
}
