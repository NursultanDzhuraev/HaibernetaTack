package java16.dao.impl;

import jakarta.persistence.EntityManager;
import java16.config.DBConfig;
import java16.dao.CommentDao;
import java16.entity.Comment;
import java16.entity.Post;
import java16.entity.User;

import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private final EntityManager entityManager = DBConfig.getEntityManagerFactory().createEntityManager();

    @Override
    public void saveComment(Long postId, Long userId, Comment comment) {
        try {
            entityManager.getTransaction().begin();

            User user = entityManager.find(User.class, userId);
            Post post = entityManager.find(Post.class, postId);
            if (user == null || post == null) {
                throw new IllegalArgumentException("User or Post not found");
            }
            comment.setPost(post);
            List<User> users = new ArrayList<>();
            users.add(user);
            comment.setUser(users);
            entityManager.persist(comment);

            if (user.getComment() == null) {
                user.setComment(new ArrayList<>());
            }
            user.getComment().add(comment);
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Comment> findCommentByPostId(Long postId) {
        Post post = entityManager.find(Post.class, postId);
        if (post == null) {
            throw new IllegalArgumentException("Post not found");
        }
        return post.getComments();
    }

    @Override
    public void updateComment(Long commentId, String newText) {
        try {
            entityManager.getTransaction().begin();
            Comment comment = entityManager.find(Comment.class, commentId);
            if (comment == null) {
                throw new IllegalArgumentException("Comment not found");
            }
            comment.setText(newText);
            entityManager.merge(comment);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteComment(Comment comment) {
        try {
            entityManager.getTransaction().begin();
            if (comment == null) {
                throw new IllegalArgumentException("Comment not found");
            }
            entityManager.remove(comment);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}
