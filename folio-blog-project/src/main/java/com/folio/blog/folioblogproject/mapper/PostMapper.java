package com.folio.blog.folioblogproject.mapper;

import com.folio.blog.folioblogproject.dto.PostDto;
import com.folio.blog.folioblogproject.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toEntity(PostDto postDto);

    PostDto toDto(Post post);
}
