package com.folio.blog.folioblogproject.repository;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageResponse {
    private PageDetails page;
    private List<?> data;
}
