package java16.dao;

import java16.entity.Comment;

import java.util.List;

public interface CommentDao {
    void saveComment(Long postId, Long userId, Comment comment);
    List<Comment> findCommentByPostId(Long postId);
    void updateComment(Long commentId, String newText);
    void deleteComment(Comment comment);
}
