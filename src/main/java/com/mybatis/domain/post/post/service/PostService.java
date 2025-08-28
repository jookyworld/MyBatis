package com.mybatis.domain.post.post.service;

import com.mybatis.domain.post.post.dto.Post;
import com.mybatis.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll("", "");
    }

    public List<Post> findAll(String title, String orderDirection) {
        return postRepository.findAll(title, orderDirection);
    }

    public Post findById(int id) {
        return postRepository.findById(id);
    }

    public int create(String title, String content) {
        postRepository.create(title, content);
        return postRepository.findLastInsertId();
    }

    public void deleteById(int id) {
        postRepository.deleteById(id);
    }

    public void update(int id, String title, String content) {
        postRepository.update(id, title, content);
    }

    public List<Post> search(String kwType, String keyword) {
        return postRepository.search(kwType, keyword);
    }

    public int deleteByIds(List<Integer> deleteIdList) {
        if (deleteIdList == null || deleteIdList.isEmpty()) {
            return 0;
        }
        return postRepository.deleteByIdList(deleteIdList);
    }
}
