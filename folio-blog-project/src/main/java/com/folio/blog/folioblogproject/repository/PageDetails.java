package com.folio.blog.folioblogproject.repository;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageDetails {
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
    private Integer number;
}
