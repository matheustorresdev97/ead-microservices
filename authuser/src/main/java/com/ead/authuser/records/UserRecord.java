package com.ead.authuser.records;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserRecord(
        UUID userId,
        String username,
        String email,
        String password,
        String oldPassword,
        String fullName,
        String phoneNumber,
        String cpf,
        String imageUrl) {
}
