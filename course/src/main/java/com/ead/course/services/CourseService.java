package com.ead.course.services;

import java.util.Optional;
import java.util.UUID;

import com.ead.course.models.CourseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CourseService {
  void delete(CourseModel courseModel);

  CourseModel save(CourseModel courseModel);

  Optional<CourseModel> findById(UUID courseId);

  Page<CourseModel> findAll(Specification<CourseModel> spec, Pageable pageable);
}