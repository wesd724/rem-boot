package com.jkb.prov1;

import com.jkb.prov1.entity.Comment;
import com.jkb.prov1.entity.Post;
import com.jkb.prov1.entity.User;
import com.jkb.prov1.repository.CommentRepository;
import com.jkb.prov1.repository.PostRepository;
import com.jkb.prov1.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class Prov1ApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @Test
    @Transactional
    void test1() {

        Post post1 = savePost("first", "hello");
        Post post2 = savePost("second", "world");

        Comment comment1 = saveComment("first11", 1L);
        Comment comment2 = saveComment("first22", 2L);
        Comment comment3 = saveComment("second11", 3L);
        Comment comment4 = saveComment("second11", 4L);

        User user = saveUser("kbb", "123", "asd");

        user.addPost(Arrays.asList(post1, post2));
        user.addComment(Arrays.asList(comment1, comment2, comment3, comment4));

        post1.addComment(Arrays.asList(comment1, comment2));
        post2.addComment(Arrays.asList(comment3, comment4));
        userRepository.save(user);

        System.out.println("BEFRORE");
        userRepository.findAll().forEach(System.out::println);
        postRepository.findAll().forEach(System.out::println);
        commentRepository.findAll().forEach(System.out::println);


        System.out.println("AFTER");
        User fuser = userRepository.findById(1L).orElseThrow();
        fuser.getPostList().remove(0);

        // orphanRemoval = true 설정 안할시에 필요
        Post dpost = postRepository.findById(1L).orElseThrow();
        postRepository.delete(dpost);

        userRepository.findAll().forEach(System.out::println);
        postRepository.findAll().forEach(System.out::println);
        commentRepository.findAll().forEach(System.out::println);
    }

    @Test
    @Transactional
    void test2() {
        User user = saveUser("ksss", "123", "zxc");
        Post post1 = savePost("fweqa", "hello"); // 1

        user.getPostList().add(post1);
        post1.setUser(user); // 영속성 전이 persist 해도 설정해줘야함.
        // 연관관계에 있는 엔티티까지 영향을 미치지만
        // 그 엔티티를 insert 연산을 수행시켜줄 뿐, 저장할 객체 연관관계는 지정해줘야 한다.
        // user의 연관관계에 저장이 발생하면, 연관관계인 post에도 저장이 일어나지만
        // setUser을 안하면, setUser을 일어나기 전 1번의 상태만 저장하기 때문에, post의 User 컬럼은 null이 된다.

        userRepository.save(user);
    }

    @Test
    @Transactional
    void test3() {
        userRepository.findById(1L).get().getPostList().get(1);
    }

    private User saveUser(String name, String password, String email) {
        User user = User
                .builder()
                .name(name)
                .password(password)
                .email(email)
                .build();
        return user;
    }

    private Post savePost(String title, String content) {
        Post post = Post
                .builder()
                .title(title)
                .text(content)
                .build();
        return post;
    }

    private Comment saveComment(String reply, Long sequence) {
        Comment comment = Comment
                .builder()
                .sequence(sequence)
                .reply(reply)
                .build();
        return comment;
    }
}
