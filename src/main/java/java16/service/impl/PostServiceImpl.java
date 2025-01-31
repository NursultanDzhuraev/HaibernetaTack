package java16.service.impl;

import java16.dao.PostDao;
import java16.dao.impl.PostDaoImpl;
import java16.entity.Post;
import java16.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {
private  final PostDao postDao=new PostDaoImpl();
    @Override
    public boolean savePost(Long userId, Post post) {
        try {
          return   postDao.savePost(userId, post);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getPostByUserId(Long userId) {
        try {
          return   postDao.getPostByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> searchPost(String query) {
        try {
          return   postDao.searchPost(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deletePostById(Long id) {
        try {
            postDao.deletePostById(id);
            return "success";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
