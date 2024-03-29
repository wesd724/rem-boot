package com.jkb.prov1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Post> postList = new ArrayList<>();;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public void setName(String name)  {
        this.name = name;
    }

    public void addPost(Post post) {
        postList.add(post);
        post.setUser(this);
    }

    public void addPost(List<Post> posts) {
        for(Post p: posts) {
            postList.add(p);
            p.setUser(this);
        }
    }

    public void addComment(Comment comment) {
        commentList.add(comment);
        comment.setUser(this);
    }

    public void addComment(List<Comment> comments) {
        for(Comment c: comments) {
            commentList.add(c);
            c.setUser(this);
        }
    }

}
