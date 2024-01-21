package com.jkb.prov1.dto;

import com.jkb.prov1.entity.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSearchResponseDto {
    private Long totalLength;
    private List<PostResponseDto> posts;

    public static PostSearchResponseDto from(Page<Post> postPage) {
        return new PostSearchResponseDto(
                postPage.getTotalElements(),
                PostResponseDto.tolist(postPage.getContent())
        );
    }
}
