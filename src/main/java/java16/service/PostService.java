package java16.service;

import java16.entity.Post;

import java.util.List;

public interface PostService {
    boolean savePost(Long userId, Post post);

    List<Post> getPostByUserId(Long userId);

    Post searchPost(String query);

    String deletePostById(Long id);
}
