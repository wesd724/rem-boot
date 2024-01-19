package com.jkb.prov1.service;

import com.jkb.prov1.dto.PostRequestDto;
import com.jkb.prov1.dto.PostResponseDto;
import com.jkb.prov1.dto.RecommendStatusDto;
import com.jkb.prov1.dto.ViewPostDto;
import com.jkb.prov1.entity.Info;
import com.jkb.prov1.entity.Post;
import com.jkb.prov1.entity.User;
import com.jkb.prov1.repository.CommentRepository;
import com.jkb.prov1.repository.PostRepository;
import com.jkb.prov1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Long savePost(Long userId, PostRequestDto postRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("id not exist"));
        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .text(postRequestDto.getText())
                .user(user)
                .info(Info.init())
                .build();
        Post savePost = postRepository.save(post);
        return savePost.getId();
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getByUserId(Long userId) {
        //User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("id not exist"));
        //return PostDto.tolist(user.getPostList());
        List<Post> posts = postRepository.findByUser_Id(userId);
        return PostResponseDto.tolist(posts);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findByTitleContaining(int page, String title, int length) {
        Page<Post> postPage = postRepository.findByTitleContaining
                (title,
                        PageRequest.of(page - 1, length,
                                Sort.by(Sort.Order.desc("id")))
                );
        return PostResponseDto.tolist(postPage.getContent());
    }

    @Transactional(readOnly = true)
    public Page<PostResponseDto> findAll(Pageable pageable) {
        Page<Post> postPage = postRepository.findAll(pageable);
        List<PostResponseDto> postResponseDto = PostResponseDto.tolist(postPage.getContent());
        return new PageImpl<>(postResponseDto, pageable, postPage.getTotalElements());
    }

    public ViewPostDto ViewPost(Long postId) {
        Post post = findByPostId(postId);
        post.getInfo().updateView();
        return ViewPostDto.from(post);
    }

    public void updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = findByPostId(postId);
        post.update(postRequestDto.getTitle(),
                postRequestDto.getText());
    }

    public void recommendPost(RecommendStatusDto recommendStatusDto) {
        Post post = findByPostId(recommendStatusDto.getPostId());
        if(recommendStatusDto.isRecommend())
            post.getInfo().updateGood();
        else post.getInfo().updateBad();

    }

    public void deletePost(Long postId) {
        commentRepository.deleteByAllCommentInPost(postId); // 영속성 전이보다 성능이 좋음
        postRepository.deleteById(postId);
    }

    private Post findByPostId(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not exist"));
    }
}
