package com.mybatis.domain.post.post.repository;

import com.mybatis.domain.post.post.dto.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostRepository {

    @Select("""
            SELECT * FROM post
            """)
    List<Post> findAll();


    @Select("""
            <script>
            SELECT * FROM post
            WHERE id = #{id}
            </script>
            """)
    Post findById(int id);

    @Insert("""
            INSERT INTO post
            SET createDate = now(),
            modifyDate = now(),
            title = #{title},
            content = #{content}
            """)
    int create(String title, String content);

    @Select("""
            SELECT LAST_INSERT_ID()
            """)
    int findLastInsertId();

    @Delete("""
            DELETE FROM post
            WHERE id = #{id}
            """)
    void deleteById(int id);

    @Update("""
            UPDATE post
            SET title = #{title},
                content = #{content},
                modifyDate = now()
           WHERE id = #{id}
            """)
    void update(
            @Param("id") int id,
            @Param("title") String title,
            @Param("content") String content);

    @Select("""
            SELECT * FROM post
            WHERE ${kwType} LIKE CONCAT('%', #{keyword},'%')
            """)
    List<Post> search(
            @Param("kwType") String kwType,
            @Param("keyword") String keyword);
}
