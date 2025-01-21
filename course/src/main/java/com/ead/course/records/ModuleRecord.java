package com.ead.course.records;

import jakarta.validation.constraints.NotBlank;

public record ModuleRecord(
        @NotBlank String title,
        @NotBlank String description) {
}
