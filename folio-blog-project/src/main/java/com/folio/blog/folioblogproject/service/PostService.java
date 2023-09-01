package com.folio.blog.folioblogproject.service;


import com.folio.blog.folioblogproject.dto.PostDto;
import com.folio.blog.folioblogproject.entity.Post;
import com.folio.blog.folioblogproject.entity.Tag;
import com.folio.blog.folioblogproject.repository.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface PostService {
    PageResponse getAllPosts(Set<Tag> tags, Pageable pageable);
    Optional<Post> getPostById(Long id);
    PostDto createPost(Post Post);
    Optional<PostDto> updatePostTags(Long id, Set<Tag> tags);
    void deletePost(Long id);
}
