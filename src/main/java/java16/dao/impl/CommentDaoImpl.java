package java16.dao.impl;

import jakarta.persistence.EntityManager;
import java16.config.DBConfig;
import java16.dao.CommentDao;
import java16.entity.Comment;
import java16.entity.Post;
import java16.entity.User;

import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private final EntityManager entityManager = DBConfig.getEntityManagerFactory().createEntityManager();

    @Override
    public void saveComment(Long postId, Long uerId, Comment comment) {
        entityManager.getTransaction().begin();
        entityManager.persist(comment);
        User user = entityManager.find(User.class, uerId);
        Post post = entityManager.find(Post.class, postId);
        entityManager.find(Comment.class, comment.getId());
        post.getComments().add(comment);
        user.getPost().add(post);
        entityManager.getTransaction().commit();

    }

    @Override
    public List<Comment> findCommentByPostId(Long postId) {
        Post post = entityManager.find(Post.class, postId);
        return post.getComments();
    }

    @Override
    public void updateComment(Long commentId, String newText) {
        entityManager.getTransaction().begin();
        Comment comment = entityManager.find(Comment.class, commentId);
        comment.setText(newText);
        entityManager.getTransaction().commit();

    }

    @Override
    public void deleteComment(Comment comment) {
        entityManager.getTransaction().begin();
        entityManager.remove(comment);
        entityManager.getTransaction().commit();
    }
}
