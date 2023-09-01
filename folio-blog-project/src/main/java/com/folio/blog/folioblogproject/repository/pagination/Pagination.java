package com.folio.blog.folioblogproject.repository.pagination;

import lombok.Builder;

import java.util.List;

public interface Pagination {

    @Builder
    record PageResponse(PageDetails page, List<?> data) {
    }

    @Builder
    record PageDetails(Integer size, Long totalElements, Integer totalPages, Integer number) {
    }
}
