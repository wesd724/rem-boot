package com.jkb.prov1.repository;

import com.jkb.prov1.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByPost_idAndId(Long postId, Long commentId);

    @Modifying(clearAutomatically = true)
    @Query("delete from Comment c where c.post.id = :postId")
    void deleteByAllCommentInPost(@Param("postId") Long id);
}
