package com.folio.blog.folioblogproject.controller;

import com.folio.blog.folioblogproject.dto.PostDto;
import com.folio.blog.folioblogproject.entity.Post;
import com.folio.blog.folioblogproject.entity.Tag;
import com.folio.blog.folioblogproject.repository.PageResponse;
import com.folio.blog.folioblogproject.service.PostService;
import com.folio.blog.folioblogproject.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final TagService tagService;

    @GetMapping
    //todo add swagger
    public ResponseEntity<PageResponse> getPosts(
            @RequestParam(required = false) Set<Long> tagIds,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Set<Tag> tags = tagService.findTagsByIds(tagIds);
        PageResponse posts = postService.getAllPosts(tags, PageRequest.of(page, size));
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> post = postService.getPostById(id);
        return post
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody Post post) {
        PostDto savedPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @PatchMapping ("/{id}/tags")
    public ResponseEntity<PostDto> updatePostTags(@PathVariable Long id, @RequestBody Set<Tag> tags) {
        Optional<PostDto> post = postService.updatePostTags(id, tags);
        return post
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
