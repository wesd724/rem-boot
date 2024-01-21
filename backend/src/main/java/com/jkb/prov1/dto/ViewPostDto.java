package com.jkb.prov1.dto;

import com.jkb.prov1.entity.Info;
import com.jkb.prov1.entity.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ViewPostDto {
    private String name;
    private Long postId;
    private String title;
    private String text;
    private Info info;
    private List<CommentResponseDto> comments;

    public static ViewPostDto from(Post post) {
        return new ViewPostDto(
                post.getUser().getName(),
                post.getId(),
                post.getTitle(),
                post.getText(),
                post.getInfo(),
                CommentResponseDto.tolist(post.getCommentList())
        );
    }
}
