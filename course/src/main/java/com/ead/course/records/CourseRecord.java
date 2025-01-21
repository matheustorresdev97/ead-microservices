package com.ead.course.records;

import java.util.UUID;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRecord(
        @NotBlank String name,

        @NotBlank String description,

        String imageUrl,

        @NotNull CourseStatus courseStatus,

        @NotNull UUID userInstructor,

        @NotNull CourseLevel courseLevel) {

}
