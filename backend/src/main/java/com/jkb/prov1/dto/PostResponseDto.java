package com.jkb.prov1.dto;

import com.jkb.prov1.entity.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostResponseDto {
    private String name;
    private Long postId;
    private String title;
    private String text;
    private Long view;

    public static PostResponseDto from(Post post) {
        return new PostResponseDto(
                post.getUser().getName(), post.getId(), post.getTitle(), post.getText(), post.getInfo().getView()
        );
    }

    public static List<PostResponseDto> tolist(List<Post> posts) {
        return posts.stream()
                .map(PostResponseDto::from)
                .toList();
    }

}
