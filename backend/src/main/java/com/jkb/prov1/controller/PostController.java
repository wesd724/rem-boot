package com.jkb.prov1.controller;

import com.jkb.prov1.dto.PostRequestDto;
import com.jkb.prov1.dto.PostResponseDto;
import com.jkb.prov1.dto.ViewPostDto;
import com.jkb.prov1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping("{userId}")
    public ResponseEntity<Long> savePost(@PathVariable Long userId, @RequestBody PostRequestDto postRequestDto) {
        Long id = postService.savePost(userId, postRequestDto);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/user")
    public ResponseEntity<List<PostResponseDto>> findByUserId(@RequestParam Long id) {
        List<PostResponseDto> posts = postService.getByUserId(id);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/s/{page}")
    public ResponseEntity<List<PostResponseDto>> findByTitleContaining
            (@PathVariable int page,
             @RequestParam(value = "search", defaultValue = "") String title,
             @RequestParam int length) {
        List<PostResponseDto> posts = postService.findByTitleContaining(page, title, length);
        return ResponseEntity.ok(posts);
    }


    @GetMapping
    public ResponseEntity<Page<PostResponseDto>> findAll(Pageable pageable) {
        Page<PostResponseDto> posts = postService.findAll(pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("{postId}")
    public ResponseEntity<ViewPostDto> ViewPost(@PathVariable Long postId) {
        ViewPostDto viewPostDto = postService.ViewPost(postId);
        return ResponseEntity.ok(viewPostDto);
    }

    @PutMapping
    public ResponseEntity<Void> updatePost(@RequestParam Long postId, @RequestBody PostRequestDto postRequestDto) {
        postService.updatePost(postId, postRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePost(@RequestParam Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
