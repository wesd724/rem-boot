package com.jkb.prov1;

import com.jkb.prov1.entity.Comment;
import com.jkb.prov1.entity.User;
import com.jkb.prov1.repository.CommentRepository;
import com.jkb.prov1.repository.PostRepository;
import com.jkb.prov1.repository.UserRepository;
import com.jkb.prov1.service.PostService;
import com.jkb.prov1.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MyTest {
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @Rollback(false)
    void test1() {
        User user = userRepository.findById(1L).get();
        user.setName("gr123a");
    }

    @Test
    @Transactional
    void test2() {
        userRepository.findById(1L).get().getCommentList().forEach(System.out::println);
    }

    @Test
    @Transactional
    @DisplayName("List and Post Lazy Loading Test")
    void test3() {
        postRepository.findAll().forEach(System.out::println);
        postRepository.findAll(PageRequest.of(0, 10)).forEach(System.out::println);
        //List든 Page든 모두 Lazy 로딩으로 가져온다.
        // 아마 모두 그냥 가져오면 부하가 많이걸려서 기본설정이 실제 컬렉션에 접근했을 때 로딩하기로 설정한 것으로 예상된다.
    }

    @Test
    @Transactional
    void ctest() {
        Comment comment = commentRepository.findByPost_idAndSequence(9L, 2L);
        System.out.println(comment);
    }

    @Test
    @Transactional
    @Rollback(false)
    void ctest2() {
        postRepository.updateGoodOrBadPoint(1L);
        assertThat(5).isEqualTo(5);

    }

}