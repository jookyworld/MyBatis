package com.mybatis.domain.post.post.repository;

import com.mybatis.domain.post.post.dto.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostRepository {

    @Select("""
            <script>
            SELECT * FROM post
            <if test="orderBy!=null and orderBy!=''">ORDER BY
            <choose>
            <when test="orderBy == 'title'">title</when>
            <when test="orderBy == 'createDate'">createDate</when>
            <when test="orderBy == 'modifyDate'">modifyDate</when>
            </choose>
            <if test="orderDirection != null and orderDirection.toLowerCase() == 'desc'">DESC</if>
            </if>
            </script>
            """)
    List<Post> findAll(
            @Param("orderBy") String orderBy,
            @Param("orderDirection") String orderDirection
    );


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
            <script>
            UPDATE post
                <set>
                     modifyDate = now(),
                     <if test="title != null and title != ''">title=#{title},</if>
                     <if test="content != null and content != ''">content=#{content}</if>
                </set>
                    <where>
                        <if test="id != null and id>0">id=#{id}</if>       
                    </where>
            </script>
            """)
    void update(
            @Param("id") int id,
            @Param("title") String title,
            @Param("content") String content);

    @Select("""
            <script>
            SELECT * FROM post
            <where>
                 <choose>
                 <when test="kwType == 'title'">
                    title LIKE CONCAT('%', #{keyword}, '%')
                 </when>
                 <when test="kwType == 'content'">
                    content LIKE CONCAT('%', #{keyword}, '%')
                 </when>
                 <otherwise>
                  (
                    title LIKE CONCAT('%', #{keyword}, '%')
                    OR
                    content LIKE CONCAT('%', #{keyword}, '%')
                  )
                 </otherwise>
                 </choose>
            </where> 
            </script>
            """)
    List<Post> search(
            @Param("kwType") String kwType,
            @Param("keyword") String keyword);
}
