package com.mybatis.domain.post.post;

import com.mybatis.domain.post.post.dto.Post;
import com.mybatis.domain.post.post.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
public class postServiceTests {

    @Autowired
    private PostService postService;

    @Test
    @DisplayName("게시물 다건 조회")
    void t1() {
        List<Post> posts = postService.findAll();

        assertThat(posts).hasSize(2);
    }

    @Test
    @DisplayName("게시물 단건 조회")
    void t2() {
        Post post = postService.findById(1);

        System.out.println(post);
        assertThat(post.getTitle()).isEqualTo("제목 1");
        assertThat(post.getContent()).isEqualTo("내용 1");
    }

    @Test
    @DisplayName("게시물 생성")
    @Transactional
    void t3() {
        int id = postService.create("제목 3", "내용 3");

        Post post = postService.findById(id);
        System.out.println(post);

        assertThat(post.getTitle()).isEqualTo("제목 3");
        assertThat(post.getContent()).isEqualTo("내용 3");
    }

    @Test
    @DisplayName("게시물 삭제")
    void t5() {
        postService.deleteById(1);

        List<Post> posts = postService.findAll();

        assertThat(posts).hasSize(1);
    }

    @Test
    @DisplayName("게시물 수정")
    void t6() {
        //given
        Post post = postService.findById(1);
        assertThat(post).isNotNull();

        //when
        postService.update(post.getId(), "수정 제목 1", "수정 내용 1");
        Post updatePost = postService.findById(1);

        //then
        assertThat(updatePost.getTitle()).isEqualTo("수정 제목 1");
        assertThat(updatePost.getContent()).isEqualTo("수정 내용 1");
    }

    @Test
    @DisplayName("게시물 제목 검색")
    void t7() {
        List<Post> posts1 = postService.search("title", "제목 1");
        System.out.println(posts1);
        assertThat(posts1).hasSize(1);

        List<Post> posts2 = postService.search("title", "제목");
        assertThat(posts2).hasSize(2);

        List<Post> posts3 = postService.search("title", "제목 2");
        assertThat(posts3).hasSize(1);
    }

    @Test
    @DisplayName("게시물 내용 검색")
    void t8() {
        List<Post> posts1 = postService.search("content", "내용 1");
        System.out.println(posts1);
        assertThat(posts1).hasSize(1);

        List<Post> posts2 = postService.search("content", "내용");
        assertThat(posts2).hasSize(2);

        List<Post> posts3 = postService.search("content", "내용 2");
        assertThat(posts3).hasSize(1);
    }

    @Test
    @DisplayName("게시물 제목 or 내용 검색")
    void t9 () {
        List<Post> posts = postService.search("", "제목 1");
        assertThat(posts).hasSize(1);

        posts = postService.search("", "제목");
        assertThat(posts).hasSize(2);

        posts = postService.search("", "제목 2");
        assertThat(posts).hasSize(1);

        posts = postService.search("", "내용 1");
        assertThat(posts).hasSize(1);

        posts = postService.search("", "내용");
        assertThat(posts).hasSize(2);

        posts = postService.search("", "내용 2");
        assertThat(posts).hasSize(1);
    }

    @Test
    @DisplayName("정렬된 게시물 조회 - 제목 오름차순")
    void t10() {
        List<Post> posts = postService.findAll("title", "asc");
        assertThat(posts).hasSize(2);
        assertThat(posts.get(0).getTitle()).isEqualTo("제목 1");
        assertThat(posts.get(1).getTitle()).isEqualTo("제목 2");
    }

    @Test
    @DisplayName("정렬된 게시물 조회 - 제목 내림차순")
    void t11() {
        List<Post> posts = postService.findAll("title", "desc");
        assertThat(posts).hasSize(2);
        assertThat(posts.get(0).getTitle()).isEqualTo("제목 2");
        assertThat(posts.get(1).getTitle()).isEqualTo("제목 1");
    }

    @Test
    @DisplayName("게시물 수정 - 일부 데이터만 수정")
    void t12() {
        //given
        Post post = postService.findById(1);
        assertThat(post).isNotNull();

        //when
        postService.update(1, "", "내용 1 수정");

        //then
        Post updatePost = postService.findById(1);

        assertThat(updatePost.getTitle()).isEqualTo("제목 1");
        assertThat(updatePost.getContent()).isEqualTo("내용 1 수정");
    }

    @Test
    @DisplayName("다중 게시물 삭제")
    void t13() {
        // given
        int id3 = postService.create("제목 3", "내용 3");
        int id4 = postService.create("제목 4", "내용 4");
        List<Post> posts = postService.findAll();
        assertThat(posts).hasSize(4);

        // when
        int deletedCount = postService.deleteByIds(Arrays.asList(id3, id4));

        // then
        assertThat(deletedCount).isEqualTo(2);

        posts = postService.findAll();
        assertThat(posts).hasSize(2); // 기존 2개만 남음
    }

}
