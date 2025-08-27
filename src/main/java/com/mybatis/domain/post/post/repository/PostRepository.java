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
    void update(int id, String title, String content);
}
