package com.jkb.prov1.controller;

import com.jkb.prov1.dto.CommentRequestDto;
import com.jkb.prov1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> saveComment(@RequestBody CommentRequestDto commentRequestDto) {
        commentService.saveComment(commentRequestDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateComment(@RequestBody CommentRequestDto commentRequestDto) {
        commentService.updateComment(commentRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteComment(@RequestBody CommentRequestDto commentRequestDto) {
        commentService.deleteComment(commentRequestDto);
        return ResponseEntity.noContent().build();
    }
}
