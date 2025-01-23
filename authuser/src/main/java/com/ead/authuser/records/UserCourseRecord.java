package com.ead.authuser.records;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UserCourseRecord(UUID userId, @NotNull UUID courseId) {

}
