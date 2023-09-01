package com.folio.blog.folioblogproject.repository;

import com.folio.blog.folioblogproject.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post p " +
            "INNER JOIN post_tags pt ON p.id = pt.post_id " +
            "WHERE pt.tag_id IN (:tagIds)", nativeQuery = true)
    Page<Post> findByTags(@Param("tagIds") Set<Long> tagIds, Pageable pageable);
}
