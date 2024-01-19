package com.jkb.prov1.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendStatusDto {
    Long postId;
    boolean recommend;
}
