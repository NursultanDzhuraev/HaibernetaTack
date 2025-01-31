package java16.dao;

import java16.entity.Post;

import java.util.List;

public interface PostDao {
    boolean savePost(Long userId, Post post);
    List<Post> getPostByUserId(Long userId);
    List<Post>  searchPost(String query);
    void deletePostById(Long id);
}
