package java16.service.impl;

import java16.dao.CommentDao;
import java16.dao.impl.CommentDaoImpl;
import java16.entity.Comment;
import java16.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao = new CommentDaoImpl();

    @Override
    public String saveComment(Long postId, Long uerId, Comment comment) {
        try {
            commentDao.saveComment(postId, uerId, comment);
            return "Successfully saved comment";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findCommentByPostId(Long postId) {
        try {
            return commentDao.findCommentByPostId(postId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateComment(Long commentId, String newText) {
        try {
            commentDao.updateComment(commentId, newText);
            return "Successfully updated comment";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteComment(Comment comment) {
        try {
            commentDao.deleteComment(comment);
            return "Successfully deleted comment";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
