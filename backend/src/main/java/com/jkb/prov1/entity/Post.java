package com.jkb.prov1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Embedded
    private Info info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Post(String title, String text, Info info, User user) {
        this.title = title;
        this.text = text;
        this.info = info;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void addComment(List<Comment> comments) {
        for(Comment c: comments) {
            commentList.add(c);
            c.setPost(this);
        }
    }

    public void update(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
