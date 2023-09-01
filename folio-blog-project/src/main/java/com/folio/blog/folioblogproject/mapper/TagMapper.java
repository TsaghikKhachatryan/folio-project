package com.folio.blog.folioblogproject.mapper;

import com.folio.blog.folioblogproject.dto.TagDto;
import com.folio.blog.folioblogproject.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag toEntity(TagDto tagDto);

    TagDto toDto(Tag post);
}
