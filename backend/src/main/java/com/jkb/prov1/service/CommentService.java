package com.jkb.prov1.service;

import com.jkb.prov1.dto.CommentRequestDto;
import com.jkb.prov1.entity.Comment;
import com.jkb.prov1.entity.Post;
import com.jkb.prov1.entity.User;
import com.jkb.prov1.repository.CommentRepository;
import com.jkb.prov1.repository.PostRepository;
import com.jkb.prov1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    public Long saveComment(CommentRequestDto commentRequestDto) {
        User user = userRepository.getByName(commentRequestDto.getName());
        Post post = findByPostId(commentRequestDto.getPostId());
        Comment comment = Comment.builder()
                .reply(commentRequestDto.getReply())
                .user(user)
                .post(post)
                .build();
        Comment saveComment = commentRepository.save(comment);
        return saveComment.getId();
    }

    public void updateComment(CommentRequestDto commentRequestDto) {
        Comment comment = findByComment(commentRequestDto.getPostId(), commentRequestDto.getCommentId());
        comment.update(commentRequestDto.getReply());
    }

    public void deleteComment(CommentRequestDto commentRequestDto) {
        Comment comment = findByComment(commentRequestDto.getPostId(), commentRequestDto.getCommentId());
        commentRepository.delete(comment);
    }

    private Post findByPostId(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no id"));
    }

    private Comment findByComment(Long postId, Long commentId) {
        return commentRepository.findByPost_idAndId(postId, commentId);
    }
}
