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
    public void saveComment(CommentRequestDto commentRequestDto) {
        User user = findByUserId(commentRequestDto.getUserId());
        Post post = findByPostId(commentRequestDto.getPostId());
        Comment comment = Comment.builder()
                .sequence(commentRequestDto.getSequence())
                .reply(commentRequestDto.getReply())
                .user(user)
                .post(post)
                .build();
        Comment saveComment = commentRepository.save(comment);
    }

    public void updateComment(CommentRequestDto commentRequestDto) {
        Comment comment = findByComment(commentRequestDto.getPostId(), commentRequestDto.getSequence());
        comment.update(commentRequestDto.getReply());
    }

    public void deleteComment(CommentRequestDto commentRequestDto) {
        Comment comment = findByComment(commentRequestDto.getPostId(), commentRequestDto.getSequence());
        commentRepository.delete(comment);
    }

    private User findByUserId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no id"));
    }

    private Post findByPostId(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no id"));
    }

    private Comment findByComment(Long postId, Long sequence) {
        return commentRepository.findByPost_idAndSequence(postId, sequence);
    }
}
