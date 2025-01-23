package com.ead.authuser.records;

import com.ead.authuser.enums.CourseLevel;
import com.ead.authuser.enums.CourseStatus;

import java.util.UUID;

public record CourseRecord(

        UUID courseId,
        String name,
        String description,
        String imageUrl,
        CourseStatus courseStatus,
        UUID userInstructor,
        CourseLevel courseLevel) {

}
