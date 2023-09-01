package com.folio.blog.folioblogproject.controller;

import com.folio.blog.folioblogproject.dto.TagDto;
import com.folio.blog.folioblogproject.entity.Tag;
import com.folio.blog.folioblogproject.service.TagService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tags")
@AllArgsConstructor
@Slf4j
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<Tag> saveTag(@Valid @RequestBody TagDto tagDto) {
        Tag savedTag = tagService.saveTag(tagDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTag);
    }
}
