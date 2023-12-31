package com.folio.blog.folioblogproject.service;

import com.folio.blog.folioblogproject.dto.PostDto;
import com.folio.blog.folioblogproject.entity.Post;
import com.folio.blog.folioblogproject.entity.Tag;
import com.folio.blog.folioblogproject.mapper.PostMapper;
import com.folio.blog.folioblogproject.repository.PostRepository;
import com.folio.blog.folioblogproject.repository.pagination.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper mapper;

    public Pagination.PageResponse getAllPosts(Set<Long> tagIds, Pageable pageable) {
        log.info("Getting all Posts");
        Page<PostDto> postDtoPage;
        if (tagIds != null && !tagIds.isEmpty()) {
            postDtoPage = postRepository.findByTags(tagIds, pageable).map(mapper::toDto);
        } else {
            postDtoPage = postRepository.findAll(pageable).map(mapper::toDto);
        }

        return Pagination.PageResponse.builder()
                .page(Pagination.PageDetails.builder()
                        .size(postDtoPage.getSize())
                        .totalElements(postDtoPage.getTotalElements())
                        .totalPages(postDtoPage.getTotalPages())
                        .number(postDtoPage.getNumber())
                        .build())
                .data(postDtoPage.getContent())
                .build();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public PostDto createPost(PostDto postDto) {
        Post post = mapper.toEntity(postDto);
        log.info("Creating Post: {}", post);
        return mapper.toDto(postRepository.save(post));
    }

    public Optional<PostDto> updatePostTags(Long id, Set<Tag> tags) {
        log.info("Updating Tags for Post with ID: {}", id);
        return postRepository.findById(id)
                .map(post -> {
                    post.setTags(tags);
                    return mapper.toDto(postRepository.save(post));
                });
    }

    public void deletePost(Long id) {
        log.info("Deleting Post: {}", id);
        postRepository.deleteById(id);
    }
}
