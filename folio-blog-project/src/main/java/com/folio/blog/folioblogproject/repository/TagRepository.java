package com.folio.blog.folioblogproject.repository;

import com.folio.blog.folioblogproject.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
