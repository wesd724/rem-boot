package com.jkb.prov1.dto;

import com.jkb.prov1.entity.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponseDto {
    private String username;
    private Long sequence;
    private String reply;

    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(comment.getUser().getName(), comment.getSequence(), comment.getReply());
    }

    public static List<CommentResponseDto> tolist(List<Comment> Comments) {
        return Comments.stream()
                .map(CommentResponseDto::from)
                .toList();
    }
}
