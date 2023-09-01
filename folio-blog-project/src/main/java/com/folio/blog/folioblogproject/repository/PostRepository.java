package com.folio.blog.folioblogproject.repository;

import com.folio.blog.folioblogproject.entity.Post;
import com.folio.blog.folioblogproject.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(nativeQuery = true, name = "Post.findByTags")
    Page<Post> findByTags(@Param("tags") Set<Tag> tags, Pageable pageable);
}
