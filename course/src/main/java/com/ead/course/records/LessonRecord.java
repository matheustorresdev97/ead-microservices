package com.ead.course.records;

import jakarta.validation.constraints.NotBlank;

public record LessonRecord(
        @NotBlank String title,
        String description,
        @NotBlank String videoUrl) {

}
