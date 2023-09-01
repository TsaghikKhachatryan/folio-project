package com.folio.blog.folioblogproject.service;

import com.folio.blog.folioblogproject.dto.TagDto;
import com.folio.blog.folioblogproject.entity.Tag;

import java.util.Set;

public interface TagService {

    Tag saveTag(TagDto tagDto);

    Set<Tag> findTagsByIds(Set<Long> tagIds);
}
