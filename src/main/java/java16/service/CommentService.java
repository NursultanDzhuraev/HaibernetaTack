package java16.service;

import java16.entity.Comment;

import java.util.List;

public interface CommentService {

    String saveComment(Long postId, Long uerId, Comment comment);

    List<Comment> findCommentByPostId(Long postId);

    String updateComment(Long commentId, String newText);

    String deleteComment(Comment comment);
}
