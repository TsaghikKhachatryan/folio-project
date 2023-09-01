package com.folio.blog.folioblogproject.service;

import com.folio.blog.folioblogproject.dto.TagDto;
import com.folio.blog.folioblogproject.entity.Tag;
import com.folio.blog.folioblogproject.mapper.TagMapper;
import com.folio.blog.folioblogproject.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class TagService{

    private final TagRepository tagRepository;
    private final TagMapper mapper;

    public TagDto createTag(TagDto tagDto) {
        log.info("Creating Tag");
        Tag tag = mapper.toEntity(tagDto);
        return mapper.toDto(tagRepository.save(tag));
    }

    public Set<Tag> findTagsByIds(Set<Long> tagIds) {
        log.info("Getting Tags by IDs: {}", tagIds);
        return tagRepository.findAllById(tagIds)
                .stream()
                .collect(toUnmodifiableSet());
    }

}
