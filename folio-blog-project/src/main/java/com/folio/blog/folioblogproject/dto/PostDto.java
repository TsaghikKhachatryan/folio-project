package com.folio.blog.folioblogproject.dto;

import com.folio.blog.folioblogproject.entity.Tag;

import java.util.Set;

public record PostDto(Long id, String title, String content, Set<Tag> tags) {
}
