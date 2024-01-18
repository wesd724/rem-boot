package com.jkb.prov1.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Info {
    private Long view;
    private Long good;
    private Long bad;

    public static Info init() {
        return new Info(0L, 0L, 0L);
    }
}
