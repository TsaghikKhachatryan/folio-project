package com.folio.blog.folioblogproject.service.impl;

import com.folio.blog.folioblogproject.dto.TagDto;
import com.folio.blog.folioblogproject.entity.Tag;
import com.folio.blog.folioblogproject.repository.TagRepository;
import com.folio.blog.folioblogproject.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableSet;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Tag saveTag(TagDto tagDto) {
        log.info("Creating Tag");
        var tag = new Tag();
        tag.setName(tagDto.name());
        return tagRepository.save(tag);
    }

    @Override
    public Set<Tag> findTagsByIds(Set<Long> tagIds) {
        log.info("Getting Tags by IDs: {}", tagIds);
        return tagRepository.findAllById(tagIds)
                .stream()
                .collect(toUnmodifiableSet());
    }

}
