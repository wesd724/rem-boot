package com.jkb.prov1.repository;

import com.jkb.prov1.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser_Id(Long id);
    Page<Post> findByTitleContaining(String Title, Pageable pageable);
}
