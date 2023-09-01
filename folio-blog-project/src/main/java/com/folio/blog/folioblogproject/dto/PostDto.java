package com.folio.blog.folioblogproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.folio.blog.folioblogproject.entity.Tag;

import java.util.Set;

public record PostDto(Long id, String title, String content, @JsonProperty(access = JsonProperty.Access.READ_ONLY) Set<Tag> tags) {
}
