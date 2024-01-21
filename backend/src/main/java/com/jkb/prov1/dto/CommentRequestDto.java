package com.jkb.prov1.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {
    private String name;
    private Long postId;
    private Long commentId;
    private String reply;
}
